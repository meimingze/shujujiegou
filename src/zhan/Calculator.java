package zhan;

public class Calculator {
    public static void main(String[] args) {
        //根据前面思路，完成表达式的运算
        String expression = "3+2*6-2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        while(true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)){
                //如果是运算符
                if(!operStack.isEmpty()){
                    //如果符号栈中有操作符，就进行比较，如果当前的操作符优先级小于或者等于栈中的操作符，就需要从数栈中pop出两个数
                    //再从栈中pop出一个符号，进行运算，将得到的结果入数字栈，当前的操作符号入符号栈
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //将当前的符号入符号栈
                        operStack.push(ch);
                    }else{
                        //如果当前的操作符优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                }else{
                    //为空直接入符号栈
                    operStack.push(ch);
                }
            }else{  //如果是数则直接入数栈
                numStack.push(ch -48);//ASC码中1对应为49
            }
            index++;
            if(index >= expression.length()){
                break;
            }
        }

    }
}
//先创建一个栈，直接使用前面创建好的
class ArrayStack2{
    private int maxsize;
    private int[] stack;
    private int top = -1;
    //构造器
    public ArrayStack2(int maxsize) {
        this.maxsize = maxsize;
        stack = new int[this.maxsize];
    }
    //增加一个方法，可以返回当前栈顶的值，但不是真正的pop
    public int peek(){
        return stack[top];
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
    //返回运算符的优先级（优先级是程序员确定），此处：优先级使用数字表示，此处数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;//假定目前的表达式只有+，-，*，/
        }
    }
    //判断是否为运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val =='*' || val == '/';
    }
    //计算方法
    public int cal(int nums1, int nums2,int oper) {
        int res = 0;//res用于存放计算的结果

        switch (oper) {
            case '+':
                res = nums1 + nums2;
            case '-' :
                res = nums2 - nums1;
            case '*':
                res = nums1 * nums2;
            case '/':
                res = nums2 / nums1;
        }
        return res;
    }
}

