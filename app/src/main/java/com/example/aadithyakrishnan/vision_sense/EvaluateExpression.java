package com.example.aadithyakrishnan.vision_sense;

import java.util.Stack;

/**
 * Created by AADITHYA  KRISHNAN on 6/27/2017.
 */

public class EvaluateExpression {

    public static Double evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Double> values = new Stack<Double>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {
            // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                i--;
                //System.out.println(sbuf.toString());
                values.push(Double.parseDouble(sbuf.toString()));
            }

            if(tokens[i]=='t')
            {
                StringBuffer sbuf1=new StringBuffer();
                i=i+3;
                while(i<tokens.length && tokens[i]>='0'&&tokens[i]<='9')
                {
                    sbuf1.append(tokens[i++]);
                }
                i--;
                String s=sbuf1.toString();
                Double value_degree=Double.parseDouble(s);
                Double value_radian=Math.toRadians(value_degree);

                Double  value=Math.tan(value_radian);
                values.push(value);

            }
            if(tokens[i]=='s')
            {
                StringBuffer sbuf1=new StringBuffer();
                i=i+3;
                while(i<tokens.length && tokens[i]>='0'&&tokens[i]<='9')
                {
                    sbuf1.append(tokens[i++]);
                }
                i--;
                String s=sbuf1.toString();
                Double value_degree=Double.parseDouble(s);
                Double value_radian=Math.toRadians(value_degree);

                Double  value=Math.sin(value_radian);
                values.push(value);

            }
            if(tokens[i]=='l')
            {
                StringBuffer sbuf1=new StringBuffer();
                i=i+3;
                while(i<tokens.length && tokens[i]>='0'&&tokens[i]<='9')
                {
                    sbuf1.append(tokens[i++]);
                }
                i--;
                String s=sbuf1.toString();
                Double value_log=Double.parseDouble(s);
                Double value=Math.log10(value_log);
                values.push(value);

            }

            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
            {
                System.out.println("enter2");
                ops.push(tokens[i]);
            }
            // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                System.out.println("enter1");
                while (ops.peek() != '(')
                {

                    // System.out.println(applyOp(ops.pop(), values.pop(), values.pop()));
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));}
                ops.pop();
            }

            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/')
            {
                System.out.println("enter3");
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    //System.out.println(applyOp(ops.pop(), values.pop(), values.pop()));
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
        {
            //System.out.println(applyOp(ops.pop(), values.pop(), values.pop()));
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }
        // Top of 'values' contains result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == 'x' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static double applyOp(char op, Double b, Double a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0.0;
    }
}



