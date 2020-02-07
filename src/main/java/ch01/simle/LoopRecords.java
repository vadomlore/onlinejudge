package simle;


import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 使用循环队列存储数据，并且能够遍历循环队列
 */
public class LoopRecords {

    public static class ErrorLineProcessor {
        private static int QUEUE_COUNT = 8;
        private ErrorEntry[]  errorRecords = new ErrorEntry[QUEUE_COUNT];
        private int head = -1, tail = -1;
        int count = 0;

        public boolean isEmpty(){
            return count == 0;
        }

        public boolean isFull(){
            return  QUEUE_COUNT <= count;
        }

        public boolean enqueue(ErrorEntry errorEntry){
            if(count == 0) {
                head++;
                tail++;
                errorRecords[tail] = errorEntry;
            }
            else {
                if(!isFull()){
                    tail++;
                    errorRecords[tail] = errorEntry;
                } else{
                    tail = (tail + 1) % QUEUE_COUNT;
                    head = (head + 1) % QUEUE_COUNT;
                    errorRecords[tail] = errorEntry;
                }
            }
            if(count < QUEUE_COUNT) {
                count++;
            }
            return true;
        }

        public ErrorEntry findErrorEntry(String name, int size){
            int pHead = head;
            int pTail = tail;
            if(isEmpty()) return null;
            if(pHead == 0 && pTail == 0){
                if(errorRecords[pHead].exists(name, size)){
                    return errorRecords[pHead];
                }
                return null;
            }

            while(pHead != pTail) {
                if(errorRecords[pHead].exists(name, size)){
                    return errorRecords[pHead];
                }
                pHead = (pHead + 1) % QUEUE_COUNT;
            }
            if(errorRecords[pHead].exists(name, size)) return errorRecords[pHead];
            return null;
        }

        public void processErrorLine(String fname, int lineNumber) {

            System.out.println("输入entry:" + new ErrorEntry(fname, lineNumber));
            ErrorEntry errorEntry = findErrorEntry(fname, lineNumber);
            if(errorEntry == null) {
                enqueue(new ErrorEntry(fname, lineNumber));
            }
            else{
                errorEntry.addNumber();
            }
        }

        public void displayErrorEntry(){
            System.out.println("<<<<<<<<<");
            int pHead = head;
            int pTail = tail;
            if(isEmpty()) return;
            if((pHead == 0 && pTail ==0)) {
                System.out.println(errorRecords[pHead]);
                return;
            }
            while( pHead != pTail ) {
                System.out.println(errorRecords[pHead]);
                pHead = (pHead + 1) % QUEUE_COUNT;
            }
            System.out.println(errorRecords[pHead]);
            System.out.println(">>>>>>>>>>");
            System.out.println();
        }
    }

    public static class ErrorEntry {
        public String fileName;

        public int lineNo;

        public int num;

        public ErrorEntry(String fileName, int lineNo) {
            this.fileName = crop(resolveFileName(fileName));
            this.lineNo = lineNo;
            this.num = 1;
        }

        public boolean exists(String fileName, int lineNo) {
            String fName = crop(resolveFileName(fileName));
            return this.fileName.equals(fName) && this.lineNo == lineNo;
        }

        public static String resolveFileName(String fullName){
            if(fullName.lastIndexOf("\\") == -1) {
                return fullName;
            }
            return fullName.substring(fullName.lastIndexOf("\\") + 1);
        }

        public static String crop(String name){
            if(name == null || name.equals("")) return "";
            if(name.length() > 16) {
                return name.substring(name.length() - 16);
            }
            return name;
        }

        public void addNumber(){
            this.num++;
        }


        @Override
        public String toString(){
            return fileName + ' ' + lineNo + ' ' + num;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ErrorLineProcessor processor = new ErrorLineProcessor();


        while (in.hasNext()) {
            String fileName = in.next();
            int lineNo = in.nextInt();
            processor.processErrorLine(fileName, lineNo);
            processor.displayErrorEntry();
        }
        processor.displayErrorEntry();
    }
}