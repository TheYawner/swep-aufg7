package de.hrw.swep.biblio.service;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import de.hrw.swep.biblio.persistence.DAO;
import de.hrw.swep.biblio.service.benutzer.Benutzer;
import de.hrw.swep.biblio.service.gegenstaende.Buch;

/**
 * Testet die Bibliotheksklasse mit der echten Datenbank.
 * 
 * @author M. Friedrich
 *
 */
public class BibliothekIntegrationsTest {
	Bibliothek bib;

	/**
	 * Before Setup
	 */
	@Before
	public void setup() {

		bib = new Bibliothek();
		bib.setDb(new DAO());
	}

	/**
	 * Testet, ob ein Buch fuer einen gegebenen Titel gefunden wird.
	 */
	@Test
	public void testSucheBuchNachTitel() {
		Set<Buch> b = bib.sucheBuchNachTitel("Herr L.");

		assertEquals("Lars Lehmann", b.iterator().next().getAutor());
	}

	/**
	 * Testet, ob ein Buch fuer einen gegebenen Autor gefunden wird.
	 */
	@Test
	public void testSucheBuchNachAutor() {
		Set<Buch> b = bib.sucheBuchNachAutor("Malte Mohn");
		assertEquals("Klatsch", b.iterator().next().getTitel());
		assertEquals("de.hrw.swep.biblio.service.gegenstaende.Ausgeliehen", b
				.iterator().next().getState().getClass().getName());
	}

	/**
	 * Testet, ob ein Benutzer mit einem gegebenen Namen gefunden wird.
	 */
	@Test
	public void testSucheBenutzerNachName() {
		Set<Benutzer> b = bib.sucheBenutzerNachName("Adalbert Alt");

		assertEquals(1, b.iterator().next().getId());
		assertEquals("de.hrw.swep.biblio.service.benutzer.Normal", b.iterator()
				.next().getStatus().getClass().getName());
	}

	/**
	 * Testet, ob ein Benutzer mit einer gegebenen ID gefunden wird.
	 */
	@Test
	public void testSucheBenutzerNachId() {
		Benutzer b = bib.sucheBenutzerNachId(1);

		assertEquals("Adalbert Alt", b.getName());

		assertEquals("de.hrw.swep.biblio.service.benutzer.Normal", b
				.getStatus().getClass().getName());
	}

}
