package br.livroandroid.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import br.livro.android.cap10.R;

/**
 * Classe auxiliar para criar uma notificacao
 * 
 * @author ricardo
 *
 * Estudado dia 11 de Abril de 2015
 *
 *  Para criar uma notificacao é necessario obter uma instancia da classe android.app.NotificationManager
 *  a qual pode ser utilizada para disparar uma notificacao e cancela-la posteriormente  com os metodos:
 *
 *    notify(int id, Notification notificacao) -> recebe um id de identificacao da notificacao e uma
 *      instancia de android.app.Notification e solicita ao Android para executar a notificacao
 *      com o id fornecido
 *
 *    cancel(int id) -> cancela a notificacao utilizando o id fornecido. Isso remove a notificacao
 *      da barra de status
 *
 *  Para criar um objeto Notification deve informar tres parametros no construtor:(deprecated na API 11)
 *      int icone -> O id do recurso do icone para exibir na barra de status da notificacao
 *
 *      CharSequence texto -> titulo do texto para exibir na barra de status na primeira vez em que
 *          a notificaçao aparece. Esse é o primeiro texto que o usuario visualiza
 *
 *      long quando -> a data e hora para exibir as notificacoes. Para exibir a notificacao no mesmo
 *          momento use o metodo System.currentTimeMillis()
 *
 *   Depois de criar uma notificacao é necessario criar um objeto android.app.PedingIntent que contem
 *   as acoes que devem ser tomadas caso o usuario a selecione. Para criar um PedingIntent qye solicita
 *   que uma intent seja aberta deve utilizar o metodo estatico PedingIntent.getActivity(contexto, codigo, intent. flags)
 *      contexto -> referencia da activity que esta criando a notificacao
 *
 *      codigo -> codigo que identifica aquisição. Nao foi utilizado
 *
 *      intent -> intent que contem as informações da activity que sera disparada caso o usuario abrir a notificacao
 *
 *      flags ->
 *
 *   Existem os metodos getActivity(...), getBroadcast(..), getService(...)
 *
 *   Para vincular o PedingIntent com a notificacao usa o metodo (deprecated na API 11)
 *   setLatestEvent(contexto, titulo, texto, intent)
 *
 *     contexto -> referencia da activity que esta criando a notificacao
 *
 *     titulo -> titulo quando usuario abre a aba de notificacoes
 *
 *     texto -> texto com detalhes da notificacao, aparece embaixo do titulo na aba de notificacoes
 *
 *     intent -> referencia da PedingIntent criada
 *
 *
 *     Apos API 11 o construtor da classe Notification foi deprecated entao deve se usar a classe
 *     Notification.Builder
 */
public class NotificationUtil {

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public static void create(Context context, String tickerText, String title, String message, int icon, int id, Intent intent) {

		// PendingIntent para executar a intent ao selecionar a notificacao
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

		Notification n = null;

		int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 11) {
			Builder builder = new Notification.Builder(context);
			builder.setContentTitle(tickerText);
			builder.setContentText(message);
			builder.setSmallIcon(icon); //icone pequeno
			builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher)); //icone grande
			builder.setContentIntent(p);

			if (apiLevel >= 17) {
				// Android 4.2
				n = builder.build();
			} else {
				// Android 3.x
				n = builder.getNotification();
			}
		} else {
			// Android 2.2
			n = new Notification(icon, tickerText, System.currentTimeMillis());

			// Informacoes
			n.setLatestEventInfo(context, title, message, p);
		}

		// id (numero unico) que identifica esta notificacao
		NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.notify(id, n);
	}

	/**
	 * API Level 17 - Android 4.2 ou superior
	 */
	@SuppressLint("NewApi")
	public static void create_v17(Context context, String tickerText, String title, String message, int icon, int id, Intent intent) {

		// PendingIntent para executar a intent ao selecionar a notifica��o
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle(tickerText);
		builder.setContentText(message);
		builder.setSmallIcon(icon);
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
		builder.setContentIntent(p);

		// Android 4.2
		Notification n = builder.build();

		// id (numero �nico) que identifica esta notifica��o
		NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.notify(id, n);
	}

	/**
	 * API Level 17 - Android 4.2 ou superior
	 */
	@SuppressLint("NewApi")
	public static void create_v17_big(Context context, String tickerText, String title, String message, String[] lines, int icon, int id,Intent intent) {

		// PendingIntent para executar a intent ao selecionar a notificacao
		PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

		Notification.Builder builder = new Notification.Builder(context);
		builder.setContentTitle(tickerText);
		builder.setContentText(message);
		builder.setSmallIcon(icon);
		builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
		builder.setContentIntent(p);

		// Cria o estilo detalhado
		Notification.InboxStyle style = new Notification.InboxStyle();
		style.setBigContentTitle(tickerText);

		for (String line : lines) {
			style.addLine(line);
		}

		// Informa o estilo
		builder.setStyle(style);
//		builder.setProgress(0, 0, true);

		// Android 4.2
		Notification n = builder.build();

		// id (numero unico) que identifica esta notificacao
		NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.notify(id, n);
	}

	public static void cancell(Context context, int id) {
		NotificationManager nm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
		nm.cancel(id);
	}
}
