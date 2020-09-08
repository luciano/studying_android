package br.livro.android.cap14.arquivos;

import android.app.backup.BackupManager;
import android.app.backup.RestoreObserver;
import android.os.Bundle;
import android.util.Log;

/**
 * Demonstra com fazer o backup do arquivo no cloud
 * 
 * @author ricardo
 * 
 */
public class ExemploSalvarArquivoBackup extends ExemploSalvarArquivo {

	private BackupManager backupManager;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		// Cria o gerenciador de backup
		backupManager = new BackupManager(this);
		backupManager.requestRestore(new RestoreObserver() {
			@Override
			public void restoreFinished(int error) {
				super.restoreFinished(error);
				Log.i("livroandroid","restoreFinished: " + error);
			}
			@Override
			public void restoreStarting(int numPackages) {
				super.restoreStarting(numPackages);
				Log.i("livroandroid","restoreStarting: " + numPackages);
			}
		});
	}

	@Override
	protected void salvar() {
		super.salvar();

		// Avisa o backup que os dados foram alterados
		backupManager.dataChanged();
	}
	
	@Override
	protected void deletar() {
		super.deletar();
		
		// Avisa o backup que os dados foram alterados
		backupManager.dataChanged();
	}
}