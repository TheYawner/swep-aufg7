package de.hrw.swep.biblio.persistence;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Set;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Before;
import org.junit.Test;

import de.hrw.swep.biblio.persistence.dto.BenutzerDTO;
import de.hrw.swep.biblio.persistence.dto.BuchDTO;

/**
 * Testklasse fuer den Datenbankzugriff
 * 
 * @author M. Friedrich
 *
 */
public class DAOTest {

  IDatabaseTester databaseTester;

  /**
   * Bringt die Datenbank in einen definierten Ausgangszustand
   * 
   * @throws Exception
   */
  @Before
  public void setup() throws Exception {
    databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver",
        "jdbc:hsqldb:file:../biblio-db/database/bibdb", "sa", "");
    databaseTester.setSetUpOperation(new HsqlDatabaseOperation());
    databaseTester.setDataSet(new FlatXmlDataSetBuilder().build(new File("full.xml")));
    databaseTester.onSetup();
  }

  /**
   * Testet das Abrufen eines bestimmten Nutzers nach der Nutzer-ID
   */
  @Test
  public void testGetUserById() {
    DAO dao = new DAO();
    BenutzerDTO b = dao.getBenutzerById(1);
    assertEquals("Adalbert Alt", b.getName());
    assertEquals("Normal", b.getStatus());
  }

  /**
   * Testet das Abrufen eines Benutzers mit einem gegebenen Namen.
   */
  @Test
  public void testGetBenutzerByName() {
    DAO dao = new DAO();
    Set<BenutzerDTO> b = dao.getBenutzerByName("Adalbert Alt");

    assertEquals(1, b.iterator().next().getId());
    assertEquals("Normal", b.iterator().next().getStatus());

  }

  /**
   * Testet das Abrufen aller Buecher eines Autors
   */
  @Test
  public void testGetBuchByAutor() {
    DAO dao = new DAO();
    Set<BuchDTO> b = dao.getBuchByAutor("Malte Mohn");
    assertEquals("Klatsch", b.iterator().next().getTitel());
    assertEquals("Ausgeliehen", b.iterator().next().getStatus());
    assertEquals(3, b.iterator().next().getId());

  }

  /**
   * Testet das Abrufen eines Buches nach dem Titel.
   */
  @Test
  public void testGetBuchByTitle() {
    DAO dao = new DAO();
    Set<BuchDTO> b = dao.getBuchByTitle("Klatsch");
    assertEquals("Malte Mohn", b.iterator().next().getAutor());
    assertEquals("Ausgeliehen", b.iterator().next().getStatus());
    assertEquals(3, b.iterator().next().getId());

  }
}
