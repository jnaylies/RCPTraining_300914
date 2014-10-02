package com.sogeti.rental.ui.views;

import java.util.Collection;

import org.eclipse.core.internal.content.Activator;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((a == null) ? 0 : a.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (a == null) {
				if (other.a != null)
					return false;
			} else if (!a.equals(other.a))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
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
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_CUSTOMER_COLOR));
		else if (element instanceof RentalObject)
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_OBJECTS_COLOR));
		else if (element instanceof Rental)
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_COLOR));
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
	
	private Color getAColor(String rgbKey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();

		// Test if a color exists for this key
		Color col = colorRegistry.get(rgbKey);
		if (col == null) {
			// if none, put a RGB for this key
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			// Get the created color by the registry
			col = colorRegistry.get(rgbKey);
		}
		return col;
	}
	
}
