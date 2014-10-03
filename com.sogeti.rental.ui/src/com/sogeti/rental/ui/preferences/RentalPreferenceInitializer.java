package com.sogeti.rental.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.sogeti.rental.ui.RentalUIActivator;
import com.sogeti.rental.ui.views.RentalUICstes;

public class RentalPreferenceInitializer extends AbstractPreferenceInitializer implements RentalUICstes {

	public RentalPreferenceInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		Color c1 = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		Color c2 = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		Color c3 = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		store.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(c1.getRGB()));
		store.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(c2.getRGB()));
		store.setDefault(PREF_OBJECTS_COLOR, StringConverter.asString(c3.getRGB()));
		
		store.setDefault(PREF_PALETTE, "com.sogeti.rental.ui.defaultPalette");

	}

}
