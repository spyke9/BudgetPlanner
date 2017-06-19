package bp.services;

import bp.model.Summary;

/**
 * Created by szkutek on 12.06.17.
 */
public class BudgetPlanner {
    private Summary estimated;
    private Summary planned;

    public Summary getEstimated() {
        return estimated;
    }

    public void setEstimated(Summary estimated) {
        this.estimated = estimated;
    }

    public Summary getPlanned() {
        return planned;
    }

    public void setPlanned(Summary planned) {
        this.planned = planned;
    }
}
