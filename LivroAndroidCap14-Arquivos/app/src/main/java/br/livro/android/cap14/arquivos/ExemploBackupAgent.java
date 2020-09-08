package br.livro.android.cap14.arquivos;

import java.io.IOException;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.FileBackupHelper;
import android.os.ParcelFileDescriptor;
import android.util.Log;

/**
 * Exemplo de BackupAgentHelper com arquivos
 * 
 * @author Ricardo Lecheta
 * 
 */
public class ExemploBackupAgent extends BackupAgentHelper {

	@Override
	public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data, ParcelFileDescriptor newState) throws IOException {
		Log.v("livroandroid","BackupAgentHelper.onBackup");
		// A classe mãe implementa isso...
		super.onBackup(oldState, data, newState);
	}

	@Override
	public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState) throws IOException {
		Log.v("livroandroid","BackupAgentHelper.onRestore");
		// A classe mãe implementa isso...
		super.onRestore(data, appVersionCode, newState);
	}

	/**
	 * @see android.app.backup.BackupAgent#onCreate()
	 */
	public void onCreate() {
		// Vamos recuperar o nome do arquivo que utilizamos para salvar
//		File f = SDCardUtils.getSdCardFile("livroandroid", "arquivo.txt");
		
		// Faz backup de um arquivo arquivo
		FileBackupHelper helper = new FileBackupHelper(this,"arquivo.txt");
		addHelper("livroandroid", helper);
	}
}