package de.musicAnalyzer.gui;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

public class Gui {
	private Text text;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void showGui(){
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setMinimumSize(new Point(600, 401));
	
		shell.setText("Music Analyzer");
		shell.setLayout(null);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(22, 41, 461, 281);
		
		Label labelTextUeberschrift = new Label(shell, SWT.NONE);
		labelTextUeberschrift.setBounds(22, 23, 60, 14);
		labelTextUeberschrift.setText("Analyse");
		
		ProgressBar progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setBounds(340, 328, 140, 14);
		
		
	
	}
}
