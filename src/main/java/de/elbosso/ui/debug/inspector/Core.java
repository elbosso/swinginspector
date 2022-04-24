/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.elbosso.ui.debug.inspector;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author elbosso
 */
public class Core extends java.awt.EventQueue implements java.awt.event.AWTEventListener
{
	private final static org.slf4j.Logger CLASS_LOGGER = org.slf4j.LoggerFactory.getLogger(Core.class);
	private final static org.slf4j.Logger EXCEPTION_LOGGER = org.slf4j.LoggerFactory.getLogger("ExceptionCatcher");
	private final static java.util.ResourceBundle i18n=java.util.ResourceBundle.getBundle("de.netsysit.ui.i18n",java.util.Locale.getDefault());
	private java.awt.Point lastPressedCoordinate;
	private javax.swing.border.Border borderLatch;
	private javax.swing.JComponent compLatch;
	private Manager manager;

	public Core(Manager manager)
	{
		super();
		this.manager=manager;
	}

	@Override
	public void dispatchEvent(java.awt.AWTEvent event)
	{
		if (event instanceof java.awt.event.MouseEvent)
		{
			java.awt.event.MouseEvent mevt = (java.awt.event.MouseEvent) event;
			{
//						if (mevt.getID() == mevt.MOUSE_PRESSED)

//							if (mevt.getID() == mevt.MOUSE_RELEASED)

//					if (mevt.getID() == mevt.MOUSE_CLICKED)

//						if (mevt.getID() == mevt.MOUSE_DRAGGED)

				java.awt.Point ppp=new java.awt.Point(mevt.getPoint());
				javax.swing.SwingUtilities.convertPointToScreen(ppp, mevt.getComponent());
				java.awt.Component comp = (java.awt.Component) event.getSource();
				java.awt.Point originalPoint = mevt.getPoint();
				comp = javax.swing.SwingUtilities.getDeepestComponentAt(comp, mevt.getX(), mevt.getY());
				if(comp!=null)
				{
					if ((mevt.getID() == mevt.MOUSE_PRESSED) && ((javax.swing.JComponent.class.isAssignableFrom(comp.getClass()) == true) && ((javax.swing.JComponent) comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore") == null))
					{
						CLASS_LOGGER.info("MOUSE_PRESSED " + comp);
						super.dispatchEvent(event);
					}
					if ((mevt.getID() == mevt.MOUSE_CLICKED) && ((javax.swing.JComponent.class.isAssignableFrom(comp.getClass()) == true) && ((javax.swing.JComponent) comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore") == null))
					{
						CLASS_LOGGER.info("MOUSE_CLICKED " + comp);
/*						if(compLatch!=null)
						{
							compLatch.setBorder(borderLatch);
							compLatch=null;
							borderLatch=null;
						}
						else
*/							super.dispatchEvent(event);
					}
					if ((mevt.getID() == mevt.MOUSE_ENTERED) && ((javax.swing.JComponent.class.isAssignableFrom(comp.getClass()) == true) && ((javax.swing.JComponent) comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore") == null))
					{
						CLASS_LOGGER.info("MOUSE_ENTERED " + comp);
						super.dispatchEvent(event);
					}
					if ((mevt.getID() == mevt.MOUSE_EXITED) && ((javax.swing.JComponent.class.isAssignableFrom(comp.getClass()) == true) && ((javax.swing.JComponent) comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore") == null))
					{
						CLASS_LOGGER.info("MOUSE_EXITED " + comp);
						super.dispatchEvent(event);
					}
					if ((mevt.getID() == mevt.MOUSE_MOVED) && ((javax.swing.JComponent.class.isAssignableFrom(comp.getClass()) == true) && ((javax.swing.JComponent) comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore") == null))
					{
						CLASS_LOGGER.info("MOUSE_MOVED " + comp);
						super.dispatchEvent(event);
					}
				}
				else
				{
					if(compLatch!=null)//&&(borderLatch!=null))
					{
						compLatch.setBorder(borderLatch);
						compLatch=null;
						borderLatch=null;
					}
				}
			}
		}
		else
		{
			super.dispatchEvent(event);
		}
		if (event instanceof java.awt.event.KeyEvent)
		{
			java.awt.event.KeyEvent kevt = (java.awt.event.KeyEvent) event;
			{
				if (((kevt.getID() == kevt.KEY_PRESSED)&&(kevt.getKeyCode()!=kevt.VK_SCROLL_LOCK))||((kevt.getID() == kevt.KEY_RELEASED)&&(kevt.getKeyCode()==kevt.VK_PRINTSCREEN)))
				{
/*						try
						{
							java.awt.Component comp = (java.awt.Component) event.getSource();
		//					comp = javax.swing.SwingUtilities.getDeepestComponentAt(comp, mevt.getX(), mevt.getY());
		//					originalPoint = javax.swing.SwingUtilities.convertPoint((java.awt.Component) mevt.getSource(), originalPoint, comp);
//							java.awt.Component root = javax.swing.SwingUtilities.getRoot(comp);
//							java.awt.Rectangle rec = new java.awt.Rectangle(root.getLocationOnScreen());
						}
						catch (Throwable ex)
						{
									ex.printStackTrace();
						}
*/				}
			}
		}
	}

	public void unregister()
	{
		unregister(true);
	}
	public void unregister(boolean deletelast)
	{
		pop();
//		FinishPanel fp=new FinishPanel(this);
	}
	public void eventDispatched(java.awt.AWTEvent event)
	{
		if (event instanceof java.awt.event.MouseEvent)
		{
			java.awt.event.MouseEvent mevt = (java.awt.event.MouseEvent) event;
			{
//						if (mevt.getID() == mevt.MOUSE_PRESSED)

//							if (mevt.getID() == mevt.MOUSE_RELEASED)

//						if (mevt.getID() == mevt.MOUSE_CLICKED)

//						if (mevt.getID() == mevt.MOUSE_DRAGGED)

				java.awt.Component comp = (java.awt.Component) event.getSource();
				java.awt.Component topmost=javax.swing.SwingUtilities.getRoot(comp);
				if ((mevt.getID() == mevt.MOUSE_PRESSED)&&((javax.swing.JComponent.class.isAssignableFrom(comp.getClass())==true)&&((javax.swing.JComponent)comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore")==null))
				{
					if(topmost!=manager.getFrame())
					{
						CLASS_LOGGER.info("MOUSE_PRESSED "+comp);
					}
				}
				if ((mevt.getID() == mevt.MOUSE_CLICKED)&&((javax.swing.JComponent.class.isAssignableFrom(comp.getClass())==true)&&((javax.swing.JComponent)comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore")==null))
				{
					if(topmost!=manager.getFrame())
					{
						CLASS_LOGGER.info("MOUSE_CLICKED " + comp);
						if(compLatch!=null)//&&(borderLatch!=null))
						{
							compLatch.setBorder(borderLatch);
							compLatch=null;
							borderLatch=null;
						}
						manager.analyze(comp);
					}
				}
				if ((mevt.getID() == mevt.MOUSE_ENTERED)&&((javax.swing.JComponent.class.isAssignableFrom(comp.getClass())==true)&&((javax.swing.JComponent)comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore")==null))
				{
					CLASS_LOGGER.info("MOUSE_ENTERED "+comp);
					if(compLatch!=null)//&&(borderLatch!=null))
					{
						compLatch.setBorder(borderLatch);
						compLatch=null;
						borderLatch=null;
					}
					if(topmost!=manager.getFrame())
					{
						compLatch = (javax.swing.JComponent) comp;
						borderLatch = ((javax.swing.JComponent) comp).getBorder();
						CLASS_LOGGER.info(java.util.Objects.toString(compLatch));
						CLASS_LOGGER.info(java.util.Objects.toString(borderLatch));
						compLatch.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createDashedBorder(Color.RED, 3, 4, 4, false), borderLatch));
					}
				}
				if ((mevt.getID() == mevt.MOUSE_EXITED)&&((javax.swing.JComponent.class.isAssignableFrom(comp.getClass())==true)&&((javax.swing.JComponent)comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore")==null))
				{
					CLASS_LOGGER.info("MOUSE_EXITED "+comp);
					if(compLatch!=null)//&&(borderLatch!=null))
					{
						compLatch.setBorder(borderLatch);
						compLatch=null;
						borderLatch=null;
					}
				}
				if ((mevt.getID() == mevt.MOUSE_MOVED)&&((javax.swing.JComponent.class.isAssignableFrom(comp.getClass())==true)&&((javax.swing.JComponent)comp).getClientProperty("de.elbosso.ui.debug.inspector.Core.ignore")==null))
				{
					if(topmost!=manager.getFrame())
					{
						CLASS_LOGGER.info("MOUSE_MOVED "+comp);
					}
				}
			}
		}
	}

	public void restart()
	{
		lastPressedCoordinate=null;
	}
}
