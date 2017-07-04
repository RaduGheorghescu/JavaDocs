package ro.teamnet.zerotohero.oop.zoo;

import java.util.Date;

/**
 * Created by Radu.Gheorghescu on 7/4/2017.
 */
public class GradomaZoo {
    private final String denumireGradinaZoo;
    private final Date dataDeschideriiGradinii;
    private AngajatZoo angajatulLunii;

    public GradomaZoo(String denumireGradinaZoo, Date dataDeschideriiGradinii) {
        this.denumireGradinaZoo = denumireGradinaZoo;
        this.dataDeschideriiGradinii = dataDeschideriiGradinii;
    }

    public String getDenumireGradinaZoo() {
        return denumireGradinaZoo;
    }


    public Date getDataDeschideriiGradinii() {
        return dataDeschideriiGradinii;
    }


    public AngajatZoo getAngajatulLunii() {
        return angajatulLunii;
    }

    public void setAngajatulLunii(AngajatZoo angajatulLunii) {
        this.angajatulLunii = angajatulLunii;
    }
}
