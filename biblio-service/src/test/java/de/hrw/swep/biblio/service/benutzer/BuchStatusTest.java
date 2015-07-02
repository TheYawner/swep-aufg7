package de.hrw.swep.biblio.service.benutzer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.hrw.swep.biblio.service.IllegalStateTransition;
import de.hrw.swep.biblio.service.gegenstaende.Ausgeliehen;
import de.hrw.swep.biblio.service.gegenstaende.Buch;
import de.hrw.swep.biblio.service.gegenstaende.Frei;
import de.hrw.swep.biblio.service.gegenstaende.Verloren;

/**
 * Testklasse fuer Statusuebergaenge des Bibliotheks-Buch
 * 
 * @author Chris
 *
 */
public class BuchStatusTest {
	String titel = "Herr der Ringe";
	String autor = "Chris";

	/**
	 * Ein Buch wird ausgeliehen: Statusuebergang "Frei" => "Ausgeliehen" wird
	 * getestet
	 */
	@Test
	public void testAusleihen() {
		Buch buch = new Buch(titel, autor);
		buch.setState(new Frei(buch));
		Benutzer b = new Benutzer(1, "TestUser");

		buch.ausleihen(b);

		assertEquals("de.hrw.swep.biblio.service.gegenstaende.Ausgeliehen",
				buch.getState().getClass().getName());
	}

	/**
	 * Ein ausgeliehenes Buch wird zurückgegeben: Statusuebergang "Ausgeliehen" =>
	 * "Frei" wird getestet
	 */
	@Test
	public void testZurueckgeben() {
		Buch buch = new Buch(titel, autor);
		buch.setState(new Ausgeliehen(buch));

		buch.zurueckgeben();

		assertEquals("de.hrw.swep.biblio.service.gegenstaende.Frei",
				buch.getState().getClass().getName());
	}

	/**
	 * Ein Nutzer im normalen Zustand kann nicht freigeschaltet werden.
	 */
	@Test(expected = IllegalStateTransition.class)
	public void testIllegalTransitionVerloren() {
		Buch buch = new Buch(titel, autor);
		buch.setState(new Verloren(buch));

		buch.verloren();

		assertEquals("de.hrw.swep.biblio.service.gegenstaende.Verloren",
				buch.getState().getClass().getName());
	}
	
	/**
	 * Ein freies Buch wird verloren.
	 */
	@Test
	public void testFreiVerlieren() {
		Buch buch = new Buch(titel, autor);
		buch.setState(new Frei(buch));

		buch.verloren();

		assertEquals("de.hrw.swep.biblio.service.gegenstaende.Verloren",
				buch.getState().getClass().getName());
	}

}
