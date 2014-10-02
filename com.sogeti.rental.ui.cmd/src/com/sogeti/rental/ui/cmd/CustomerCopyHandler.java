package com.sogeti.rental.ui.cmd;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.opcoach.training.rental.Customer;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CustomerCopyHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public CustomerCopyHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection) {
			IStructuredSelection isel = (IStructuredSelection) currentSelection;
			String customers = "";
			for (Iterator<?> it = isel.iterator(); it.hasNext();) {
				Object o = it.next();
				if (o instanceof Customer) {
					Customer c = (Customer) o;
				 	Clipboard clipboard = new Clipboard(Display.getCurrent());
					customers += "\n" + c.getDisplayName();
					String rtfData = "{\\rtf1\\b\\ i " + customers +"}";
					TextTransfer textTransfer = TextTransfer.getInstance();
					RTFTransfer rtfTransfer = RTFTransfer.getInstance();
					Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
					Object[] data = new Object[]{customers, rtfData};
					clipboard.setContents(data, transfers, DND.CLIPBOARD);
					clipboard.dispose();

				}
			}
		}

		return null;
	}
}
