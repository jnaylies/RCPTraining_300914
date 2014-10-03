package com.sogeti.rental.ui.palettes;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.sogeti.rental.ui.RentalUIActivator;

public class YellowPalette implements IColorProvider {

	public YellowPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		// return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_CUSTOMER_COLOR));
		return Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
	}

}
