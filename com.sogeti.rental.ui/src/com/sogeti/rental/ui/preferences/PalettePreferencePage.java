package com.sogeti.rental.ui.preferences;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.sogeti.rental.ui.Palette;
import com.sogeti.rental.ui.RentalUIActivator;
import com.sogeti.rental.ui.views.RentalUICstes;

public class PalettePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, RentalUICstes {

	public PalettePreferencePage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
	}

	public PalettePreferencePage(int style) {
		super(style);
		// TODO Auto-generated constructor stub
	}

	public PalettePreferencePage(String title, int style) {
		super(title, style);
		// TODO Auto-generated constructor stub
	}

	public PalettePreferencePage(String title, ImageDescriptor image, int style) {
		super(title, image, style);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		// Extract the double String for name and provider (value is the key)
		Map<String, Palette> palettes = RentalUIActivator.getDefault().getPaletteManager();
		
		String[][]comboValues = new String[palettes.size()][2];
		int i = 0;
		for (Palette p : palettes.values()) {
			comboValues[i][0] = p.getName();
			comboValues[i][1] = p.getId();
			i++;
		}
		
		addField(new ComboFieldEditor(PREF_PALETTE, "Palette couleur :", comboValues, getFieldEditorParent()));
	}

}
