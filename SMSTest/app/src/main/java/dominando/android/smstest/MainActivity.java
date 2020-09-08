package dominando.android.smstest;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
// http://stackoverflow.com/questions/1976252/how-to-use-sms-content-provider-where-are-the-docs
    String colName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tView = (TextView)findViewById(R.id.textView);

        ContentResolver cr =getContentResolver();
        Uri uri = Uri.parse("content://sms/inbox");
        //Uri uri = Uri.parse("content://sms"); -- For all SMS
        //Uri uri = Uri.parse("content://sms/sent"); -- For all Sent Items
        //If you want to read the Sent SMS then change the URi to /sent.

        //In this example we are using Query as we have defined URi as above.
        //We have declared all the Column names we need in string array in the second parameter.
        //If you dont need all then leave null
        //Notice that we did not call managedQuery instead we used Query method of ContentResolver
        final Cursor messagesCursor = cr.query(uri, new String[] { "_id","address","body","person"}, null,null, null);
        colName = "ColumnName" +"\n";
        colName = colName +  "--------------" + "\n";

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int loopCounter=0; loopCounter < messagesCursor.getColumnCount() ; loopCounter++)
                {
                    colName = colName + messagesCursor.getColumnName(loopCounter) + "\n";

                }
                colName = colName +  "--------------" + "\n";

                if(messagesCursor.getCount() > 0)
                {
                    while(messagesCursor.moveToNext())
                    {
                        colName = colName +  messagesCursor.getString(messagesCursor.getColumnIndex("body")) + "--";
                        colName = colName +  messagesCursor.getString(messagesCursor.getColumnIndex("address")) + "\n";
                    }
                }
                //tView.setText(colName);
                Log.d("LogLS", colName);
            }
        }).start();

    }
}
