package com.sogeti.rental.ui.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalCustomerView extends ViewPart implements ISelectionListener {
	private Label customerFirstName;
	private Label customerLastName;

	public RentalCustomerView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));

		Label customerFirstNameLabel = new Label(infoGroup, SWT.NONE);
		customerFirstNameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		customerFirstNameLabel.setText("First Name :");

		customerFirstName = new Label(infoGroup, SWT.NONE);
		customerFirstName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		customerFirstName.setText("John Wayne");

		Label customerLastNameLabel = new Label(infoGroup, SWT.NONE);
		customerLastNameLabel.setText("Last Name :");

		customerLastName = new Label(infoGroup, SWT.NONE);
		customerLastName.setText("John Wayne");

		setCustomer(RentalCoreActivator.getAgency().getCustomers().get(0));

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void setCustomer(Customer c) {
		customerFirstName.setText(c.getFirstName());
		customerLastName.setText(c.getLastName());
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			Customer c = ((Customer) Platform.getAdapterManager().getAdapter(selected, Customer.class));
			if (c != null)
				setCustomer(c);
		}
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	public void setLabelAsDragSource(final Label label) {
	}
}
