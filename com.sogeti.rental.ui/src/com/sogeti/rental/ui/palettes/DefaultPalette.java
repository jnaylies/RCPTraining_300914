package com.sogeti.rental.ui.palettes;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalUIActivator;
import com.sogeti.rental.ui.views.RentalProvider.Node;
import com.sogeti.rental.ui.views.RentalUICstes;

public class DefaultPalette implements IColorProvider, RentalUICstes {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
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
