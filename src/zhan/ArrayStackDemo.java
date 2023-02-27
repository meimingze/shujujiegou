package zhan;

import java.util.Scanner;

/**
 * 1.栈是先入后出的有序列表
 * 2.栈允许插入和删除的一端为变化的一端，称为栈顶。另一端为固定端称为栈底
 * 3.最先放入栈中的元素在栈底，最后放入的元素在栈顶，而删除元素相反，最后放入的最先删除，最先放入的最后删除
 * 4.出栈（pop） 入栈（push）
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试ArrayStack是否正确
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈");
            System.out.println("pop 表示从栈取出数据");
            System.out.println("请输入你的选择");
        }
        switch (key){
            case "show":
                stack.list();
                break;
            case "push":
                System.out.println("请输入一个数");
                int value = scanner.nextInt();
                stack.push(value);
                break;
            case "pop":
                try {
                    int res = stack.pop();
                    System.out.printf("出栈的数据是%d\n",res);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "exit":
                scanner.close();
                loop = false;
                break;

            default:
                break;
        }

    }
}
class ArrayStack{
    private int maxsize;
    private int[] stack;
    private int top = -1;
    //构造器
    public ArrayStack(int maxsize) {
        this.maxsize = maxsize;
        stack = new int[this.maxsize];
    }
    //栈满
    public boolean isFull(){
        return top == maxsize - 1;

    }
    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }
    //出栈，将栈顶数据返回
    public int  pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈的情况，遍历栈，需要从栈顶开始遍历数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for(int i = top; i >= 0; i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}
