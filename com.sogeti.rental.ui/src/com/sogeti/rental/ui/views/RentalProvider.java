package com.sogeti.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalUIActivator;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, RentalUICstes, IColorProvider {
	
	public class Node {
		private String label;
		private RentalAgency a;
		
		public Node(String label, RentalAgency a) {
			super();
			this.label = label;
			this.a = a;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public RentalAgency getA() {
			return a;
		}

		public void setA(RentalAgency a) {
			this.a = a;
		}

		
		Object[] getChildren() {
			if (label.equals(CUSTOMER))
				return a.getCustomers().toArray();
			else if (label.equals(LOCATIONS))
				return a.getRentals().toArray();
			else if (label.equals(OBJETS_LOUES))
				return a.getObjectsToRent().toArray();
			return null;
		}
		@Override
		public String toString() {
			return label;
		}
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		if (inputElement instanceof Collection<?>)
			return ((Collection) inputElement).toArray();
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RentalAgency) {
			RentalAgency a = (RentalAgency) parentElement;
			return new Node[] {new Node(CUSTOMER,a), new Node(LOCATIONS,a), new Node(OBJETS_LOUES,a)};
		} else if (parentElement instanceof Node) {
			Node n = (Node) parentElement;
			return n.getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return true;
	}
	
	@Override
	public String getText(Object element) {
		if(element instanceof RentalAgency)
			return ((RentalAgency) element).getName();
		else if(element instanceof Customer)
			return ((Customer) element).getDisplayName();
		else if (element instanceof RentalObject)
			return ((RentalObject) element).getName();

		return super.getText(element);
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer)
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		else if (element instanceof RentalObject)
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		else if (element instanceof Rental)
			return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		else if (element instanceof Node)
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
		return null;
	}
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof Node) {
			Node n = (Node) element;
			if (n.getLabel().equals(CUSTOMER))
				return RentalUIActivator.getDefault().getImageRegistry().get(ICON_CUSTOMER);
			if (n.getLabel().equals(LOCATIONS))
				return RentalUIActivator.getDefault().getImageRegistry().get(ICON_RENTALS);
			if (n.getLabel().equals(OBJETS_LOUES))
				return RentalUIActivator.getDefault().getImageRegistry().get(ICON_RENTAL_OBJECTS);
			return null;
		} else if (element instanceof RentalAgency)
			return RentalUIActivator.getDefault().getImageRegistry().get(ICON_AGENCY);
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
