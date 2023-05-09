package pl.milenamrugala;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Task {
    private String description;
    private String priority;
    private LocalDate startDate;
    private LocalDate endDate;

    public Task(String description, String priority, LocalDate startDate, LocalDate endDate) {
        this.description = description;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return description + " \n(Priority: " + priority + ", Start date: " + startDate +
                ", End date: " + endDate + ", " + getTimeLeft() + ")";
    }

    public String getTimeLeft() {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), endDate);
        if (daysLeft == 0) {
            return Color.RED + "Due today" + Color.RESET;
        } else if (daysLeft == 1) {
            return  Color.RED + "Due tomorrow" + Color.RESET;
        } else if (daysLeft < 0) {
            return Color.RED + "Past due" + Color.RESET;
        } else if (daysLeft <= 31) {
            return Color.YELLOW + "Due in " + daysLeft + " days" + Color.RESET;
        } else {
            return Color.GREEN + "Due in " + daysLeft + " days" + Color.RESET;
        }
    }
}
