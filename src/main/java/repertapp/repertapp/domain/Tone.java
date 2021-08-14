package repertapp.repertapp.domain;

public enum Tone {
    A("A"), Ab("Ab"), Asus("A#"),
    B("B"), Bb("Bb"),
    C("C"), Csus("C#"),
    D("D"), Db("Db"), Dsus("D#"),
    F("F"), Fsus("F#"),
    E("E"), Eb("Eb"),
    G("G"), Gb("Gb"), Gsus("G#"),
    Am("Am"), Abm("Abm"), Asusm("A#m"),
    Bm("Bm"), Bbm("Bbm"),
    Cm("Cm"), Csusm("C#m"),
    Dm("Dm"), Dbm("Dbm"), Dsusm("D#m"),
    Em("Em"), Ebm("Ebm"),
    Fm("Fm"), Fsusm("F#m"),
    Gm("Gm"), Gbm("Gbm"), Gsusm("G#m");

    public String tone;

    Tone(String tone) {
        this.tone = tone;
    }

}
