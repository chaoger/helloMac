package com.chaoger.struct.stack;

import java.util.Queue;
import java.util.Stack;

public class MainStack {


    //1.两个栈实现一个队列
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;
    /** Push element x to the back of queue. */
    public void offer(int x) {
        stackPush.push(x);

    }

    /** Removes the element from in front of queue and returns that element. */
    public int poll() {
        if(stackPop.empty()){
            while (!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();

    }

    /** Get the front element. */
    public int topQueue() {

        if(stackPop.empty()){
            while (!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();

    }

    /** Returns whether the queue is empty. */
    public boolean emptyQueue() {
        return stackPop.empty()&& stackPush.empty();

    }

    //2.两个队列实现一个栈
    private Queue<Integer> queue;
    private Queue<Integer> tmp;


    /** Push element x onto stack. */
    public void push(int x) {
        if(queue.isEmpty()){
            queue.offer(x);
        }else{
            while(!queue.isEmpty()){
                tmp.offer(queue.poll());
            }
            queue.offer(x);
            while(!tmp.isEmpty()){
                queue.offer(tmp.poll());
            }
        }

    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();

    }

    /** Get the top element. */
    public int topStack() {
        return queue.peek()==null ? 0 : queue.peek();

    }

    /** Returns whether the stack is empty. */
    public boolean emptyStack() {
        return queue.peek()==null ;

    }

    //3.有效的括号表达式 []()[]
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i<s.length();i++) {
            char c = s.charAt(i);
            if(c=='(' || c=='[' || c == '{'){
                stack.push(c);
            }else {
                if(stack.isEmpty()) return false;
                switch (c){
                    case ')':
                        if(stack.pop()=='('){
                            continue;
                        }else {
                            return false;
                        }
                    case ']':
                        if(stack.pop()=='['){
                            continue;
                        }else {
                            return false;
                        }
                    case '}':
                        if(stack.pop()=='{'){
                            continue;
                        }else {
                            return false;
                        }
                    default:
                        return false;
                }
            }
        }

        return stack.isEmpty();

    }
}
