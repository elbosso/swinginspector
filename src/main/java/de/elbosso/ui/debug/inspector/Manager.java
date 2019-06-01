/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.elbosso.ui.debug.inspector;

import de.elbosso.util.Utilities;
import org.apache.log4j.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author elbosso
 */
public class Manager extends Object 
{
	private final static org.apache.log4j.Logger CLASS_LOGGER = org.apache.log4j.Logger.getLogger(Manager.class);
	private final static org.apache.log4j.Logger EXCEPTION_LOGGER = org.apache.log4j.Logger.getLogger("ExceptionCatcher");
	private Core core;
	private static Manager sharedInstace;
	private javax.swing.Action registerAction;
	private javax.swing.JFrame frame;
	private javax.swing.JPanel ifHolder;
	private de.netsysit.util.beans.InterfaceFactory interfaceFactory;

	public static Manager getSharedInstance()
	{
		if(sharedInstace==null)
		{
			sharedInstace=new Manager();
		}
		return sharedInstace;
	}
	private int lastWrittenNumber;


	private Manager()
	{
		super();
		createActions();
		frame=new javax.swing.JFrame("Manager");
		core=new Core(this);
		frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		javax.swing.JToolBar tb=new javax.swing.JToolBar();
		tb.setFloatable(false);
		tb.add(registerAction);
		javax.swing.JPanel p=new javax.swing.JPanel(new java.awt.BorderLayout());
		p.add(tb, BorderLayout.NORTH);
		ifHolder=new javax.swing.JPanel(new java.awt.BorderLayout());
		p.add(ifHolder);
		interfaceFactory=new InspectorInterfaceFactory(javax.swing.JComponent.class);
		frame.setContentPane(p);
		frame.pack();
		frame.setVisible(true);
	}

	public JFrame getFrame()
	{
		return frame;
	}

	private void createActions()
	{
		registerAction=new javax.swing.AbstractAction("register")
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Manager.getSharedInstance().register();
			}
		};
	}
	public Core register()
	{
		if(core!=null)
		{
			registerAction.setEnabled(false);
			java.awt.Toolkit.getDefaultToolkit().getSystemEventQueue().push(core);
			java.awt.Toolkit.getDefaultToolkit().addAWTEventListener(core, java.awt.AWTEvent.MOUSE_EVENT_MASK|java.awt.AWTEvent.KEY_EVENT_MASK);
//			javax.swing.ToolTipManager.sharedInstance().setEnabled(false);
			core.restart();
		}
		return core;
	}
	public void analyze(java.awt.Component comp)
	{
		unregister();
		try
		{
			ifHolder.removeAll();
			if(CLASS_LOGGER.isInfoEnabled())CLASS_LOGGER.info(comp);
			java.awt.Component i=interfaceFactory.fetchInterfaceForBean(comp,comp.getName());
			if(CLASS_LOGGER.isInfoEnabled())CLASS_LOGGER.info(i);
			ifHolder.add(i);//new javax.swing.JScrollPane(i));
			if(ifHolder.getParent()!=null)
			{
				ifHolder.getParent().invalidate();
				ifHolder.getParent().validate();
				ifHolder.getParent().doLayout();
				ifHolder.getParent().repaint();
			}
			else
			{
				if(CLASS_LOGGER.isEnabledFor(Level.WARN))CLASS_LOGGER.warn("no parent for "+ifHolder);
			}
		}
		catch(de.netsysit.util.beans.InterfaceFactoryException exp)
		{
			Utilities.handleException(EXCEPTION_LOGGER,comp,exp);
		}
	}
	public void unregister()
	{
		if(core!=null)
		{
			java.awt.Toolkit.getDefaultToolkit().removeAWTEventListener(core);
			core.unregister(false);
//			javax.swing.ToolTipManager.sharedInstance().setEnabled(true);
			registerAction.setEnabled(true);
		}
	}
	public static void premain(String agentArgs)
	{
//		de.elbosso.util.Utilities.configureBasicStdoutLogging(Level.ALL);
		if(CLASS_LOGGER.isEnabledFor(org.apache.log4j.Level.ERROR))CLASS_LOGGER.error("premain)");
		Manager.getSharedInstance();
	}
}
