/*******************************************************************************
 *     Copyright 2016 Vasiliy Altunin aka SkyRanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package skyranger.game.desktop;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import skyranger.game.MainGameClass;
import skyranger.game.bridge.GameMouseEvent;
import skyranger.game.bridge.IGameMouseListener;
import skyranger.game.bridge.SwtEvents;
import skyranger.game.objects.IBox2dObject;

import com.badlogic.gdx.math.Vector2;

public class MainWindow {

	protected Shell shell;
	final MainGameClass mainClass;
	private Button btnDebugF;
	private Text wheelAmount;
	private Text id;
	private Text ang;
	private Text y;
	private Text x;
	private Text h;
	private Text w;

	public MainWindow(MainGameClass mainClass) {
		this.mainClass = mainClass;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow(new MainGameClass());
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		while (!this.mainClass.isLoaded) {
			// we just wait while all stuff created by LIBGDX
		}
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Debug window");
		
				Label lblMouse = new Label(shell, SWT.NONE);
				lblMouse.setBounds(369, 10, 55, 15);
				lblMouse.setText("Mouse");
		
		Label lblRmb = new Label(shell, SWT.RIGHT);
		lblRmb.setBounds(287, 127, 55, 15);
		lblRmb.setText("RMB");
		
				Label lblX = new Label(shell, SWT.RIGHT);
				lblX.setBounds(315, 37, 27, 15);
				lblX.setText("x");
		
		Label lblWheel = new Label(shell, SWT.RIGHT);
		lblWheel.setBounds(287, 148, 55, 15);
		lblWheel.setText("Wheel");
		
		final Canvas rmb = new Canvas(shell, SWT.NONE);
		rmb.setBounds(348, 126, 76, 16);
		
		Label lblCmb = new Label(shell, SWT.RIGHT);
		lblCmb.setBounds(287, 106, 55, 15);
		lblCmb.setText("MMB");
		
				Label lblY = new Label(shell, SWT.RIGHT);
				lblY.setBounds(315, 64, 27, 15);
				lblY.setText("y");
		
				final Text mouseY = new Text(shell, SWT.BORDER | SWT.CENTER);
				mouseY.setBounds(348, 58, 76, 21);
		
		wheelAmount = new Text(shell, SWT.BORDER | SWT.CENTER);
		wheelAmount.setText("0");
		wheelAmount.setBounds(348, 148, 76, 21);
		
		final Canvas mmb = new Canvas(shell, SWT.NONE);
		mmb.setBounds(348, 106, 76, 16);
		
		final Canvas lmb = new Canvas(shell, SWT.NONE);
		lmb.setBounds(348, 85, 76, 16);
		
		Label lblNewLabel = new Label(shell, SWT.RIGHT);
		lblNewLabel.setBounds(287, 85, 55, 15);
		lblNewLabel.setText("LMB");
		
				final Text mouseX = new Text(shell, SWT.BORDER | SWT.CENTER);
				mouseX.setBounds(348, 31, 76, 21);

		btnDebugF = new Button(shell, SWT.CHECK);
		btnDebugF.setSelection(mainClass.getPlayScreen().isDebug);

		btnDebugF.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Button btn = (Button) arg0.getSource();
				// System.out.println(btn.getSelection());

				// arg0.detail = SWT.CHECK
				mainClass.getPlayScreen().isDebug = btn.getSelection();

			}
		});

		btnDebugF.setBounds(339, 235, 85, 16);
		btnDebugF.setText("Debug - F12");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(10, 37, 27, 15);
		lblNewLabel_1.setText("ID");
		
		Label lblAngl = new Label(shell, SWT.NONE);
		lblAngl.setText("ANG");
		lblAngl.setBounds(10, 180, 27, 15);
		
		Label lblY_1 = new Label(shell, SWT.NONE);
		lblY_1.setText("Y");
		lblY_1.setBounds(10, 94, 27, 15);
		
		Label lblW = new Label(shell, SWT.NONE);
		lblW.setText("X");
		lblW.setBounds(10, 67, 27, 15);
		
		id = new Text(shell, SWT.BORDER);
		id.setBounds(43, 34, 76, 21);
		
		ang = new Text(shell, SWT.BORDER);
		ang.setBounds(43, 177, 76, 21);
		
		y = new Text(shell, SWT.BORDER);
		y.setBounds(43, 91, 76, 21);
		
		x = new Text(shell, SWT.BORDER);
		x.setBounds(43, 64, 76, 21);
		
		h = new Text(shell, SWT.BORDER);
		h.setBounds(43, 150, 76, 21);
		
		w = new Text(shell, SWT.BORDER);
		w.setBounds(43, 123, 76, 21);
		
		Label lblWe = new Label(shell, SWT.NONE);
		lblWe.setText("W");
		lblWe.setBounds(10, 126, 27, 15);
		
		Label lblH = new Label(shell, SWT.NONE);
		lblH.setText("H");
		lblH.setBounds(10, 153, 27, 15);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SwtEvents.changeObjectAngle(id.getText(), Float.valueOf(ang.getText()));
				SwtEvents.changeObjectPosition(id.getText(), new Vector2(Float.valueOf(x.getText()),Float.valueOf(y.getText())));
				SwtEvents.changeObjectSize(id.getText(), new Vector2(Float.valueOf(w.getText()),Float.valueOf(h.getText())));
			}
		});
		btnNewButton.setBounds(44, 204, 75, 25);
		btnNewButton.setText("Apply");

		GameMouseEvent
				.addListener(new IGameMouseListener() {

					@Override
					public void mouseMoved(final Vector2 position) {
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								mouseX.setText(String.valueOf(position.x));
								mouseY.setText(String.valueOf(position.y));
							}
						});
					}

					@Override
					public void leftMouseClicked() {
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								lmb.setBackground(new Color(null, 255, 0, 0));
							}
						});
						//System.out.println("left");					
					}

					@Override
					public void middleMouseClicked() {
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								mmb.setBackground(new Color(null, 255, 0, 0));
							}
						});
						//System.out.println("middle");						
					}

					@Override
					public void rigthMouseClicked() {
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								rmb.setBackground(new Color(null, 255, 0, 0));
							}
						});
						//System.out.println("right");					
						}

					@Override
					public void mouseReleased() {
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								lmb.setBackground(new Color(null, 240, 240, 240));
								mmb.setBackground(new Color(null, 240, 240, 240));
								rmb.setBackground(new Color(null, 240, 240, 240));
							}
						});
						//System.out.println("release");					
					}

					@Override
					public void mouseWheelMoved(final int amount) {
							Display.getDefault().asyncExec(new Runnable() {
								@Override
								public void run() {
									wheelAmount.setText(String.valueOf(Integer.valueOf(wheelAmount.getText())+amount));
								}
							});
					}

					@Override
					public void mouseClickOnObject(final IBox2dObject obj) {
						Display.getDefault().asyncExec(new Runnable() {
							@Override
							public void run() {
								id.setText(obj.getId());
								x.setText(String.valueOf(obj.getPosition().x));
								y.setText(String.valueOf(obj.getPosition().y));
								w.setText(String.valueOf(obj.getSize().x));
								h.setText(String.valueOf(obj.getSize().y));
								ang.setText(String.valueOf(obj.getAngle()));
							}
							});
						
					}

				});

	}
}
