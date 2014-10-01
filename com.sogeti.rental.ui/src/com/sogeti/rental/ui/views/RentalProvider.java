package com.sogeti.rental.ui.views;

import java.awt.Label;
import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, RentalUICstes {
	
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
			if (label == CUSTOMER)
				return a.getCustomers().toArray();
			else if (label == LOCATIONS)
				return a.getRentals().toArray();
			else if (label == OBJETS_LOUES)
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
}
