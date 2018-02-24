package scoretracker.robert.scheffel.eu.scoretraker.activity;

public class LvParcour {

    private int nr;
    private int countTargets;
    private String parcourName;

    public LvParcour(int nr, int countTargets, String parcourName) {
        this.nr = nr;
        this.countTargets = countTargets;
        this.parcourName = parcourName;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getCountTargets() {
        return countTargets;
    }

    public void setCountTargets(int countTargets) {
        this.countTargets = countTargets;
    }

    public String getParcourName() {
        return parcourName;
    }

    public void setParcourName(String parcourName) {
        this.parcourName = parcourName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LvParcour)) return false;

        LvParcour lvParcour = (LvParcour) o;

        if (nr != lvParcour.nr) return false;
        if (countTargets != lvParcour.countTargets) return false;
        return parcourName != null ? parcourName.equals(lvParcour.parcourName) : lvParcour.parcourName == null;
    }

    @Override
    public int hashCode() {
        int result = nr;
        result = 31 * result + countTargets;
        result = 31 * result + (parcourName != null ? parcourName.hashCode() : 0);
        return result;
    }
}
