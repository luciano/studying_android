package br.edu.ufvjm.sigatest;
/**
 * Created by Greg Kochaniak, http://www.hyperionics.com
 * License: public domain.
 */

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public abstract class WebArchiveReader {
    private static final String TAG = WebArchiveReader.class.toString();
    private Document myDoc = null;
    private static boolean myLoadingArchive = false;
    private WebView myWebView = null;
    private final ArrayList<String> urlList = new ArrayList<String>();
    private final ArrayList<Element> urlNodes = new ArrayList<Element>();

    private final WebViewClient originalClient;

    public WebArchiveReader(WebViewClient originalClient) {
        this.originalClient = originalClient;
    }

    protected abstract void onFinished(WebView webView);

    public boolean readWebArchive(InputStream is) {
        DocumentBuilderFactory builderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        myDoc = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myDoc = builder.parse(is);
            NodeList nl = myDoc.getElementsByTagName("url");
            for (int i = 0; i < nl.getLength(); i++) {
                Node nd = nl.item(i);
                if (nd instanceof Element) {
                    Element el = (Element) nd;
                    // siblings of el (url) are: mimeType, textEncoding, frameName, data
                    NodeList nodes = el.getChildNodes();
                    for (int j = 0; j < nodes.getLength(); j++) {
                        Node node = nodes.item(j);
                        if (node instanceof Text) {
                            String dt = ((Text) node).getData();
                            byte[] b = Base64.decode(dt, Base64.DEFAULT);
                            dt = new String(b);
                            urlList.add(dt);
                            urlNodes.add((Element) el.getParentNode());
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to read web archive", e);
            myDoc = null;
        }
        return myDoc != null;
    }

    private byte[] getElBytes(Element el, String childName) {
        try {
            Node kid = el.getFirstChild();
            while (kid != null) {
                if (childName.equals(kid.getNodeName())) {
                    Node nn = kid.getFirstChild();
                    if (nn instanceof Text) {
                        String dt = ((Text) nn).getData();
                        return Base64.decode(dt, Base64.DEFAULT);
                    }
                }
                kid = kid.getNextSibling();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean loadToWebView(final WebView v, final String anchorLink, final String historyUrl) {
        myWebView = v;
        try {
            myWebView.setWebViewClient(new WACWebClient());
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setDefaultTextEncodingName("UTF-8");

            myLoadingArchive = true;

            // Find the first ArchiveResource in myDoc, should be <ArchiveResource>
            Element ar = (Element) myDoc.getDocumentElement().getFirstChild().getFirstChild();
            byte b[] = getElBytes(ar, "data");

            // Find out the web page charset encoding
            String charset = null;
            String topHtml = new String(b).toLowerCase();
            int n1 = topHtml.indexOf("<meta http-equiv=\"content-type\"");
            if (n1 > -1) {
                int n2 = topHtml.indexOf('>', n1);
                if (n2 > -1) {
                    String tag = topHtml.substring(n1, n2);
                    n1 = tag.indexOf("charset");
                    if (n1 > -1) {
                        tag = tag.substring(n1);
                        n1 = tag.indexOf('=');
                        if (n1 > -1) {
                            tag = tag.substring(n1 + 1);
                            tag = tag.trim();
                            n1 = tag.indexOf('\"');
                            if (n1 < 0)
                                n1 = tag.indexOf('\'');
                            if (n1 > -1) {
                                charset = tag.substring(0, n1).trim();
                            }
                        }
                    }
                }
            }

            if (charset != null)
                topHtml = new String(b, charset);
            else {
                topHtml = new String(b);
                /*
                 * CharsetMatch match = new CharsetDetector().setText(b).detect();
				 * if (match != null)
				 * try {
				 * Lt.d("Guessed enc: " + match.getName() + " conf: " + match.getConfidence());
				 * topHtml = new String(b, match.getName());
				 * } catch (UnsupportedEncodingException ue) {
				 * topHtml = new String(b);
				 * }
				 */
            }
            String baseUrl = Util.SanitizeBaseUrl(new String(getElBytes(ar, "url")));
            if (!Util.isStringNullOrEmpty(anchorLink)) {
                String[] urlParts = baseUrl.split("#", 2);
                if (urlParts.length == 2) {
                    baseUrl = urlParts[0] + "#" + anchorLink;
                }
            }
            Log.d(TAG, "Base Url: " + baseUrl);
            v.loadDataWithBaseURL(baseUrl, topHtml, "text/html", "UTF-8", historyUrl);
        } catch (Exception e) {
            Log.e(TAG, "Failed to load web archive", e);
            myWebView.setWebViewClient(originalClient);
            return false;
        }
        return true;
    }

    private class WACWebClient extends WebViewClient {

        @SuppressLint("NewApi")
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (!myLoadingArchive)
                return null;

            int n = urlList.indexOf(url);
            if (n < 0)
                return null;

            Element parentEl = urlNodes.get(n);
            byte[] b = getElBytes(parentEl, "mimeType");
            String mimeType = b == null ? "text/html" : new String(b);
            b = getElBytes(parentEl, "textEncoding");
            String encoding = b == null ? "UTF-8" : new String(b);
            b = getElBytes(parentEl, "data");
            return new WebResourceResponse(mimeType, encoding, new ByteArrayInputStream(b));
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // our WACWebClient is no longer needed in view
            view.setWebViewClient(originalClient);
            myLoadingArchive = false;
            onFinished(myWebView);
        }
    }
}