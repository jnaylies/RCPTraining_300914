package com.sogeti.rental.ui.views;

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

import com.opcoach.training.rental.Rental;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart implements ISelectionListener {

	private Label rentedObjectLabel;
	private Label customerName;
	private Label dateDebutTexte;
	private Label dateFinTexte;

	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));

		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.CENTER;
		rentedObjectLabel.setLayoutData(gd);
		setLabelAsDragSource(rentedObjectLabel);

		Label customerLabel = new Label(infoGroup, SWT.NONE);
		customerLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		customerLabel.setText("Lou\u00E9 \u00E0 :");

		customerName = new Label(infoGroup, SWT.NONE);
		customerName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		customerName.setText("John Wayne");

		Group grpDatesPrvues = new Group(parent, SWT.NONE);
		grpDatesPrvues.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpDatesPrvues.setText("Dates pr\u00E9vues");
		grpDatesPrvues.setLayout(new GridLayout(3, false));

		Label dateDebutLabel = new Label(grpDatesPrvues, SWT.NONE);
		dateDebutLabel.setText("du :");
		new Label(grpDatesPrvues, SWT.NONE);

		dateDebutTexte = new Label(grpDatesPrvues, SWT.NONE);
		dateDebutTexte.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dateDebutTexte.setText("15/03/2011");

		Label dateFinLabel = new Label(grpDatesPrvues, SWT.NONE);
		dateFinLabel.setText("au :");
		new Label(grpDatesPrvues, SWT.NONE);

		dateFinTexte = new Label(grpDatesPrvues, SWT.NONE);
		dateFinTexte.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dateFinTexte.setText("22/03/2011");

		setRental(RentalCoreActivator.getAgency().getRentals().get(0));

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void setRental(Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
		customerName.setText(r.getCustomer().getDisplayName());
		dateDebutTexte.setText(r.getStartDate().toString());
		dateFinTexte.setText(r.getEndDate().toString());
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			if (selected instanceof Rental)
				setRental((Rental) selected);
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
		DragSource source = new DragSource(label, DND.DROP_MOVE | DND.DROP_COPY);

		// Define the transfer type
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });

		// Add a drag lister to source
		source.addDragListener(new DragSourceAdapter() {
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = label.getText();
				}
			}
		});
	}
}
