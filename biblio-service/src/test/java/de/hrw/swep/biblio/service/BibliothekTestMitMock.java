package de.hrw.swep.biblio.service;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;
import de.hrw.swep.biblio.persistence.DBInterface;
import de.hrw.swep.biblio.persistence.dto.BenutzerDTO;
import de.hrw.swep.biblio.persistence.dto.BuchDTO;
import de.hrw.swep.biblio.service.benutzer.Benutzer;
import de.hrw.swep.biblio.service.gegenstaende.Buch;

/**
 * Testet die Bibliotheks-Klasse mit einem Mock-Objekt
 * @author Sandra
 *
 */
public class BibliothekTestMitMock {
	
	Bibliothek bib;
	
	/**
	 * Mocks
	 */
	@Before
	public void setUp() {
		DBInterface dbi = Mockito.mock(DBInterface.class);
		
		BenutzerDTO b = new BenutzerDTO(1, "Chris Pfeffi", "Normal");
		Set<BenutzerDTO> bSet = new HashSet<BenutzerDTO>();
		bSet.add(b);
		
		BuchDTO buch = new BuchDTO(1, "Stinson", "Playbook", "Frei");
		Set<BuchDTO> buchSet = new HashSet<BuchDTO>();
		buchSet.add(buch);
		
		when(dbi.getBenutzerById(1)).thenReturn(b);
		when(dbi.getBenutzerByName("Chris Pfeffi")).thenReturn(bSet);
		when(dbi.getBuchByAutor("Stinson")).thenReturn(buchSet);
		when(dbi.getBuchByTitle("Playbook")).thenReturn(buchSet);
		
		
		
		bib = new Bibliothek();
		bib.setDb(dbi);
		
	}

	/**
	 * Testet, ob ein Buch mit gegebenem Titel gefunden wird.
	 */
	@Test
	public void testSucheBuchNachTitel() {
		Set<Buch> b = bib.sucheBuchNachTitel("Playbook");
		assertEquals("Stinson", b.iterator().next().getAutor());
		assertEquals(1, b.size());
	}

	/**
	 * Testet, ob ein Buch mit gegebenem Autor gefunden wird.
	 */
	@Test
	public void testSucheBuchNachAutor() {
		Set<Buch> b = bib.sucheBuchNachAutor("Stinson");
		assertEquals(1, b.size());
		assertEquals("Playbook", b.iterator().next().getTitel());
	}

	/**
	 * Testet, ob ein Benutzer mit gegebenem Namen gefunden wird.
	 */
	@Test
	public void testSucheBenutzerNachName() {
		Set<Benutzer> bSet = bib.sucheBenutzerNachName("Chris Pfeffi");
		assertEquals(1, bSet.size());
		assertEquals(1, bSet.iterator().next().getId());
	}

	/**
	 * Testet, ob ein Benutzer mit einer gegebenen ID gefunden wird.
	 */
	@Test
	public void testSucheBenutzerNachId() {
		Benutzer b = bib.sucheBenutzerNachId(1);
		assertEquals("Chris Pfeffi", b.getName());
		assertEquals("de.hrw.swep.biblio.service.benutzer.Normal", b.getStatus().getClass().getName());
		
	}

}
