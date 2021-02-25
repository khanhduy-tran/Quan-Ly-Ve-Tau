package trankhanhduy.pro;

import java.util.Scanner;

public class nhapmang {
	
	public void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int []arr;
		System.out.println("Mời bạn nhập vào số phần tử tối đa");
		int n=scan.nextInt();
		arr=new int[n];
		System.out.println("Mời bạn nhập giá trị cho mảng :");
		for(int i=0;i<arr.length;i++)
		{
			System.out.print("arr["+i+"] =");
			arr[i]=scan.nextInt();
		}
		System.out.println("Mảng sau khi bạn nhập là:"); 
		for(int i=0;i<arr.length;i++)           
		{
			System.out.print(arr[i]+"\t");
			
		}
		
	}

}
