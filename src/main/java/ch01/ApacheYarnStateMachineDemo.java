
import java.util.EventObject;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 状态机需要定义两个实体，以及考察接口api
 *
 * //返回下一个状态
 * 1.class State
 *
 *    (1) 状态迁移函数 State handle(StateMachine stateMachine, Event event);
 *    (2) 进入当前状态接续处理函数 enter(StateMachine stateMachine)
 *    (2) 退出当前出状态接续处理函数 exit(StateMachine stateMachine)
 *
 * 2. Statemachine class
 *    State currentState
 *    handle(Event event) {
 *      State newState = currentState.handle(this, event);
 *      if(newState != null) {
 *          currentState.exit(); //optional
 *          currentState = newState;
 *          currentState.enter(); //optional
 *      }
 *    }
 */
public class ApacheYarnStateMachineDemo {

    public  static class RmContainer{
        public EventObject raise(){
            return new ContaninerAllocatedEvent(this);
        }
    }

    public static class RmApp {

        public EventObject startEvent(){
            return new StartEvent(this);
        }

        public EventObject stopEvent(){
            return new KillEvent(this);
        }

        public RmAppAttempt init(){
            return RmAppAttempt.create();
        }
    }

    public static class ApplicationMasterLauncher{

        public EventObject raise(){
            return new LaunchedEvent(this);
        }
    }

    public abstract static class State {


        public abstract String getName();

        //处理event, 返回下一个状态
        public  State handleEvent(RmAppAttempt rmAppAttempt, EventObject event) {
            if(this != null && this.getClass() != NoneState.class && this.getClass() != FinishedState.class && event instanceof KillEvent){
                return new KilledState();
            }
            return doHandleEvent(rmAppAttempt, event);
        }


        public abstract State doHandleEvent(RmAppAttempt rmAppAttempt, EventObject event);



        @Override
        public String toString() {
            return "State{" +
                    "name='" + getName() + '\'' +
                    '}';
        }

        //进入下一个状态
        public void enter(RmAppAttempt rmAppAttempt){
            System.out.println("currentState: " + getName());
        }
    }

    public static class StartEvent extends EventObject {
        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public StartEvent(Object source) {
            super(source);
        }
    }



    public static class APPAcceptedEvent extends EventObject {
        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public APPAcceptedEvent(Object source) {
            super(source);
        }
    }

    public static class KillEvent extends EventObject {
        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public KillEvent(Object source) {
            super(source);
        }
    }

    public static class FinishedEvent extends EventObject {
        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public FinishedEvent(Object source) {
            super(source);
        }
    }


    public static class ContaninerAllocatedEvent extends EventObject {
        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public ContaninerAllocatedEvent(Object source) {
            super(source);
        }
    }

    public static class LaunchedEvent extends EventObject {
        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public LaunchedEvent(Object source) {
            super(source);
        }
    }


    /**
     *
     */

    public static class ResourceScheduler {

        public EventObject raise(){
            return new APPAcceptedEvent(this);
        }

        public EventObject finish(){
            return new FinishedEvent(this);
        }
    }

    public static class SubmittedState extends State {


        @Override
        public String getName() {
            return "Submitted";
        }

        public State doHandleEvent(RmAppAttempt rmAppAttempt, EventObject event) {
            if(event.getSource().getClass() == ResourceScheduler.class && event instanceof APPAcceptedEvent)
            {
                return new ScheduledState();
            }
            return null;
        }

    }

    public static class ScheduledState extends State {

        @Override
        public String getName() {
            return "ScheduledState";
        }

        @Override
        public State doHandleEvent(RmAppAttempt rmAppAttempt, EventObject event) {
            if(event.getSource().getClass() == RmContainer.class && event instanceof ContaninerAllocatedEvent)
            {
                return new AllocatedState();
            }
            else if(event.getSource().getClass() == ApplicationMasterLauncher.class && event instanceof LaunchedEvent) {
                return new RunningState();
            }
            return null;
        }

    }

    public static class AllocatedState extends State {

        @Override
        public String getName() {
            return "AllocatedState";
        }

        @Override
        public State doHandleEvent(RmAppAttempt rmAppAttempt, EventObject event) {

            return null;
        }

    }

    public static class KilledState extends State {

        @Override
        public String getName() {
            return "KilledState";
        }

        @Override
        public State doHandleEvent(RmAppAttempt rmAppAttempt, EventObject event) {

            return null;
        }

    }

    public static class RunningState extends State {

        @Override
        public String getName() {
            return "RunningState";
        }

        @Override
        public State doHandleEvent(RmAppAttempt rmAppAttempt, EventObject event) {
            if(event.getSource().getClass() == ResourceScheduler.class && event instanceof FinishedEvent)
            {
                return new FinishedState();
            }
            return null;
        }

    }

    public static class FinishedState extends State {

        @Override
        public String getName() {
            return "FinishedState";
        }

        @Override
        public State doHandleEvent(RmAppAttempt rmAppAttempt, EventObject event) {

            return null;
        }

    }

    public static class NoneState extends State{

        @Override
        public String getName() {
            return "NoneState";
        }

        @Override
        public State doHandleEvent(RmAppAttempt rmAppAttempt, EventObject event) {
            if(event.getSource().getClass() == RmApp.class && event instanceof StartEvent){
                return new SubmittedState();
            }
            return null;
        }

    }

    //状态机
    public static class RmAppAttempt {

        State currentState;

        public void handleEvent(EventObject event){
            State state = currentState.handleEvent(this, event);
            if(state != null){
                currentState = state;
                currentState.enter(this);
            }
        }

        public static RmAppAttempt create(){
            RmAppAttempt appAttempt = new RmAppAttempt();
            appAttempt.currentState = new NoneState();
            return appAttempt;
        }
    }

    public static class MockState{

        public Queue<EventObject> events = new LinkedBlockingQueue<>();

        public void start(){

            RmApp rmApp = new RmApp();

            RmAppAttempt rmAppAttempt = rmApp.init();
            rmAppAttempt.handleEvent(rmApp.startEvent());
            rmAppAttempt.handleEvent(new ResourceScheduler().raise());
            rmAppAttempt.handleEvent(new RmContainer().raise());
            rmAppAttempt.handleEvent(new ApplicationMasterLauncher().raise());
            rmAppAttempt.handleEvent(new ResourceScheduler().finish());
            rmAppAttempt.handleEvent(new RmApp().stopEvent());


            System.out.println("=========");

            rmAppAttempt = rmApp.init();
            rmAppAttempt.handleEvent(rmApp.startEvent());
            rmAppAttempt.handleEvent(new ResourceScheduler().raise());
            rmAppAttempt.handleEvent(new ApplicationMasterLauncher().raise());
            rmAppAttempt.handleEvent(new ResourceScheduler().finish());
            rmAppAttempt.handleEvent(new RmApp().stopEvent());

            System.out.println("=========");

            rmAppAttempt = rmApp.init();
            rmAppAttempt.handleEvent(rmApp.startEvent());
            rmAppAttempt.handleEvent(new RmApp().stopEvent());
        }
    }

    public static void main(String[] args) {
        new MockState().start();
    }
}
