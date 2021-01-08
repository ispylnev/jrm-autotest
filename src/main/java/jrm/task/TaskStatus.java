package jrm.task;

public enum TaskStatus {

    /**
     * Task is ready to be tested by system.
     */
    READY,

    /**
     * The testing process is running and the task will be tested soon,
     * this status used to exclude the timepoint when the system runs more then one test process
     */
    IN_PROGRESS,

    /**
     * Completed means that the task successfully completed by user.
     */
    COMPLETED,

    /**
     * Failed means that the task has been failed by some reasons.
     */
    FAILED,

    /**
     * Blocked means that the task is not available for the user.
     * For example: user makes more tries when possible or this task in the module,
     * which isn't paid by user.
     */
    BLOCKED

}
