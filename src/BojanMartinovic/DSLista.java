package BojanMartinovic;

import labis.cvorovi.CvorDSListe;
import labis.exception.LabisException;
import labis.liste.ADSLista;
import labis.test.ListGenerator;

public class DSLista extends ADSLista {
	/**
	 * Proizvod neparnih elemenata kojima su i prethodnik i sledbenik parni u DS
	 * listi
	 */

	public int proizvod(CvorDSListe k) throws LabisException {
		if (k == null)
			throw new LabisException();
		if (prvi.sledeci == null && prvi.podatak % 2 != 0)
			return prvi.podatak;

		int proizvod = 1;
		boolean promenjen = false;
		if (prvi.podatak % 2 != 0 && prvi.sledeci.podatak % 2 == 0) {
			proizvod *= prvi.podatak;
			promenjen = true;
		}
		CvorDSListe pom = prvi.sledeci;
		while (pom.sledeci != null) {
			if (pom.podatak % 2 != 0 && pom.prethodni.podatak % 2 == 0 && pom.sledeci.podatak % 2 == 0) {
				proizvod *= pom.podatak;
				promenjen = true;
			}
			pom = pom.sledeci;
		}
		if (pom.podatak % 2 != 0 && pom.prethodni.podatak % 2 == 0) {
			proizvod *= pom.podatak;
			promenjen = true;
		}
		if (!promenjen)
			throw new LabisException("Nema takvih elementa");
		return proizvod;
	}

	/**
	 * vratiti pokazivac na element DS liste za koji vazi da je razlika zbirova svih
	 * levo od njega i svih desno od njega maksimalna.
	 * 
	 */

	public CvorDSListe maxRazlikaZbirova(CvorDSListe k) throws LabisException{
		if (k == null || k.sledeci == null)
			return k;

		int max = Integer.MIN_VALUE;
		CvorDSListe pom = null;
		int zbirl = 0;
		int zbird = 0;
		int zbir = 0;
		while (k.sledeci != null) {
			zbir += k.podatak;
			k = k.sledeci;
		}
		zbir += k.podatak;
		while (k != null) {
			if (k.sledeci != null)
				zbird += k.sledeci.podatak;
			zbirl = zbir - zbird - k.podatak;
			if (max < zbirl - zbird) {
				max = zbirl - zbird;
				pom = k;
			}
			k = k.prethodni;
		}
		return pom;
	}

	public static void main(String[] args) {
		int niz[] = { -35, -55, -4, 6, -1 };
		DSLista l = new DSLista();
		ListGenerator.napraviDSListuCommon(l, niz, false);
		ListGenerator.ispisiDSListu(l.prvi);

		System.out.println("\n\n");
		try {
			System.out.println(l.maxRazlikaZbirova(l.prvi).podatak);
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
