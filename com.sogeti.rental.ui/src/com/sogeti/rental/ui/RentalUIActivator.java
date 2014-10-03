package com.sogeti.rental.ui;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import com.sogeti.rental.ui.views.RentalUICstes;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements RentalUICstes {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sogeti.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;
	
	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		// RCP - 090
		readViewExtensions();
	}

	private void readViewExtensions() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = reg.getExtensionPoint("org.eclipse.ui.views");
		
		for (IExtension extension : extensionPoint.getExtensions()) {
			for (IConfigurationElement e : extension.getConfigurationElements()) {
				if (e.getName().equals("view")) {
					String vue = e.getAttribute("name");
					String plugin = e.getNamespaceIdentifier();
					System.out.println("Plugin : "+plugin+"\tVue : "+vue);
				}
			}
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}
	
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(getClass());
		
		reg.put(ICON_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(ICON_CUSTOMER)));
		reg.put(ICON_AGENCY, ImageDescriptor.createFromURL(b.getEntry(ICON_AGENCY)));
		reg.put(ICON_RENTAL_OBJECTS, ImageDescriptor.createFromURL(b.getEntry(ICON_RENTAL_OBJECTS)));
		reg.put(ICON_RENTALS, ImageDescriptor.createFromURL(b.getEntry(ICON_RENTALS)));
	}

}
