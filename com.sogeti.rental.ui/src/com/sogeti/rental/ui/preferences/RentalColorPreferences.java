package com.sogeti.rental.ui.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.sogeti.rental.ui.RentalUIActivator;
import com.sogeti.rental.ui.views.RentalUICstes;

public class RentalColorPreferences extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage,RentalUICstes {

	public RentalColorPreferences() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Rental UI Preference");
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, CUSTOMER, getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_COLOR, LOCATIONS, getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_OBJECTS_COLOR, OBJETS_LOUES, getFieldEditorParent()));
	}

}
