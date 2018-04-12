package pl.desz.bootifulapp.model;

public enum IncidentPriority {

    CRITICAL(1), MAJOR(3), MINOR(7);

    private int daysToResolve;

    IncidentPriority(int daysToResolve) {
        this.daysToResolve = daysToResolve;
    }

    public int getDaysToResolve() {
        return daysToResolve;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IncidentPriority{");
        sb.append("daysToResolve=").append(daysToResolve);
        sb.append('}');
        return sb.toString();
    }
}
