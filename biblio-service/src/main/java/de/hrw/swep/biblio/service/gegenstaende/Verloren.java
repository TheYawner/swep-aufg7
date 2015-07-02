// CHECKSTYLE:OFF

package de.hrw.swep.biblio.service.gegenstaende;

import de.hrw.swep.biblio.service.IllegalStateTransition;
import de.hrw.swep.biblio.service.benutzer.Benutzer;

public class Verloren implements Ausleihstatus {

	@SuppressWarnings("unused")
	private Gegenstand gegenstand;

	public Verloren(Gegenstand g) {
		this.gegenstand = g;
	}

	public void ausleihen(Benutzer user) {
		throw new IllegalStateTransition();
	}

	public void zurueckgeben() {
		throw new IllegalStateTransition();
	}

	public void verloren() {
		throw new IllegalStateTransition();
	}


}
