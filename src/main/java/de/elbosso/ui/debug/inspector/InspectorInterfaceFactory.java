/*
 * Copyright (c) 2019.
 *
 * Juergen Key. Alle Rechte vorbehalten.
 *
 * Weiterverbreitung und Verwendung in nichtkompilierter oder kompilierter Form,
 * mit oder ohne Veraenderung, sind unter den folgenden Bedingungen zulaessig:
 *
 *    1. Weiterverbreitete nichtkompilierte Exemplare muessen das obige Copyright,
 * die Liste der Bedingungen und den folgenden Haftungsausschluss im Quelltext
 * enthalten.
 *    2. Weiterverbreitete kompilierte Exemplare muessen das obige Copyright,
 * die Liste der Bedingungen und den folgenden Haftungsausschluss in der
 * Dokumentation und/oder anderen Materialien, die mit dem Exemplar verbreitet
 * werden, enthalten.
 *    3. Weder der Name des Autors noch die Namen der Beitragsleistenden
 * duerfen zum Kennzeichnen oder Bewerben von Produkten, die von dieser Software
 * abgeleitet wurden, ohne spezielle vorherige schriftliche Genehmigung verwendet
 * werden.
 *
 * DIESE SOFTWARE WIRD VOM AUTOR UND DEN BEITRAGSLEISTENDEN OHNE
 * JEGLICHE SPEZIELLE ODER IMPLIZIERTE GARANTIEN ZUR VERFUEGUNG GESTELLT, DIE
 * UNTER ANDEREM EINSCHLIESSEN: DIE IMPLIZIERTE GARANTIE DER VERWENDBARKEIT DER
 * SOFTWARE FUER EINEN BESTIMMTEN ZWECK. AUF KEINEN FALL IST DER AUTOR
 * ODER DIE BEITRAGSLEISTENDEN FUER IRGENDWELCHE DIREKTEN, INDIREKTEN,
 * ZUFAELLIGEN, SPEZIELLEN, BEISPIELHAFTEN ODER FOLGENDEN SCHAEDEN (UNTER ANDEREM
 * VERSCHAFFEN VON ERSATZGUETERN ODER -DIENSTLEISTUNGEN; EINSCHRAENKUNG DER
 * NUTZUNGSFAEHIGKEIT; VERLUST VON NUTZUNGSFAEHIGKEIT; DATEN; PROFIT ODER
 * GESCHAEFTSUNTERBRECHUNG), WIE AUCH IMMER VERURSACHT UND UNTER WELCHER
 * VERPFLICHTUNG AUCH IMMER, OB IN VERTRAG, STRIKTER VERPFLICHTUNG ODER
 * UNERLAUBTE HANDLUNG (INKLUSIVE FAHRLAESSIGKEIT) VERANTWORTLICH, AUF WELCHEM
 * WEG SIE AUCH IMMER DURCH DIE BENUTZUNG DIESER SOFTWARE ENTSTANDEN SIND, SOGAR,
 * WENN SIE AUF DIE MOEGLICHKEIT EINES SOLCHEN SCHADENS HINGEWIESEN WORDEN SIND.
 *
 */

package de.elbosso.ui.debug.inspector;

import de.netsysit.ui.beans.BeansUIPanel;

import java.beans.PropertyDescriptor;

public class InspectorInterfaceFactory extends de.netsysit.util.beans.InterfaceFactory
{
	private final static org.apache.log4j.Logger CLASS_LOGGER=org.apache.log4j.Logger.getLogger(InspectorInterfaceFactory.class);
	private final static org.apache.log4j.Logger EXCEPTION_LOGGER=org.apache.log4j.Logger.getLogger("ExceptionCatcher");
	private java.lang.Class defaultStopClass;
	private int maxDepth=1;
	private int depthCounter;

	public InspectorInterfaceFactory()
	{
		super();
	}

	public InspectorInterfaceFactory(Class defaultStopClass)
	{
		super();
		this.defaultStopClass=defaultStopClass;
	}

	protected java.lang.Class determineDefaultStopClass(java.lang.Class beanClass)
	{
		Class c = beanClass;
		Class d = super.determineDefaultStopClass(c);
		if(defaultStopClass!=null)
		{
			if(defaultStopClass.isAssignableFrom(c))
			{
				d=defaultStopClass.getSuperclass();
			}
		}
		return d;
	}

	@Override
	protected void reset()
	{
		CLASS_LOGGER.error("reset");
		super.reset();
		depthCounter=0;
	}

	@Override
	protected void fillPanelsWithMembersAndConnect(BeansUIPanel beansuipanel, PropertyDescriptor[] pds, Object bean, Boolean editable) throws InstantiationException, IllegalAccessException
	{
		++depthCounter;
		CLASS_LOGGER.error("depthCounter: "+depthCounter);
		super.fillPanelsWithMembersAndConnect(beansuipanel, pds, bean, editable);
		--depthCounter;
	}

	@Override
	protected boolean isMaximumDepthReached()
	{
		return ((super.isMaximumDepthReached())||(depthCounter>=maxDepth));
	}
}
