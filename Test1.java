import java.util.Scanner; 

public class Test1 {
	public static void main(String[] args) {
	calculator calc = new calculator();
	calc.Vivod();
	}
}

class calculator extends operand{
	// вывод введенных данных
	private String str = vvod();
	void Vivod() {
		int pos_op=search_op(str);
		int res = super.result(str, pos_op, str.length());
		print_res(res);
	}
	
	// процедура ввода строки
	private String vvod() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ввод выражения: ");
		String str = sc.nextLine();
		return str;
	}
	// процедура поиска знака операции  возвращает его позицию в строке
	private int search_op(String str) {
		int index,index2;
		String symv= "+-*/" ;
		search :{ // поиск знака
			index = str.indexOf("+");
			if(index!=-1) break search;
			index = str.indexOf("-");
			if(index!=-1) break search;
			index = str.indexOf("*");
			if(index!=-1) break search;
			index = str.indexOf("/");
		}
		//Проводим проверку на наличие еще одного знака операции
		//Программа поддерживает только один знак операции в выражении;
		if(index!=-1) {
			index2=-1;
			for(int i=0,fl=0; i<4;i++) {
				// поиск последнего знака в ствроке
				index2= str.lastIndexOf(symv.charAt(i));
				if(index2!=-1) {
					if(fl==1 || index2!=index) {
						System.out.println("Ошибка: Найдено несколько знаков операции");
						System.exit(-2);
					}
					fl=1; // при нахождении первого знака продолжаем поиск
					// до нахождения второго;
				}
			}
		}
		else {
			System.out.println(" Ошибка: не найден знак операции");
			System.exit(-1); 
		}
		
		return index;		
	}
}

// класс содержит методы возвращающие операнд типа int
class operand {
	private int mas_sym[][]= {
			{1,73}, // I 73
			{5,86}, // V 86
			{10,88}, // X 88
			{50,76}, //L 76
			{100,67}, //C 67
			{500,68}, //D 68
			{1000,77} //M 77
	};
	static int type=0,type2=0;
	// метод возвращает операнд 1
	int op(String str,int pos) {
		int numeral=0,w=0;
		char sym[] = new char[3];
		int mas_numeral[] = new int[3];
		if(pos!=0) {
			String op1 = str.substring(0,pos);
			// проверяем все ли символы арабские
			for(int i=pos-1,p=1; i>-1;i--) {
				 sym[0]= op1.charAt(i);
				 while(i>-1) {
					 if(sym[0]==32) {  //символ пробел?
						 if(i>0)i--;
						 else return numeral;
						 sym[0]= op1.charAt(i);
					 }
					 else break;
				 }
				 if(i>-1) {
					 if(sym[0]>47 && sym[0]<58) { // арабская цифра
						 numeral+= Integer.parseInt(Character.toString(sym[0]))*p;
						 p*=10;
						 type=1; // флаг значит что была арабская цифра
					 }
					 else if(type==1) {
						 System.out.println("Ошибка: операнд 1 разные символы");
						 System.exit(-4);
					 }
					 else break; // последняя римская 
				 }
				 
			}
			if(type==1) return numeral; // выход цифры были арабские
			//ввод римских цифр.
			type=0;
			int pos_numeral=0;
			int mas_len=op1.length();
			for(int i=0;i<pos;i++) {
				if(pos-i>2) { 
					// запись римских цифр игнорируя пробелы
					int v=0;
					while(i+v<mas_len && w<3) { // проверка не вышли ли за пределы массива
						sym[w++] = op1.charAt(i+v);
						if(sym[w-1]==32) w--; // если пробел в следующем 
						// проходе перезапись
						v++;
					}
					if(w==3) // если записали 3 римских цифры или других
					{
						pos_numeral=0;
						for(int z=0;z<3;z++)
							for(int k=0;k<7;k++)
								if(sym[z]==mas_sym[k][1]) {
									// запись 10х эквивалентов
									mas_numeral[pos_numeral++]=mas_sym[k][0];
									break;
								}
									
						if(pos_numeral<3) { //между римскими непонятные символы
							System.out.println("Ошибка : неверный оператор 1");
							System.exit(-5);
						}
						else {// вычисление числа в 10с/c;
							if(mas_numeral[0]>=mas_numeral[1]) {
								if(mas_numeral[2]>mas_numeral[1]) {
									numeral+=mas_numeral[2]-mas_numeral[1]+mas_numeral[0];
								i+=2;
								}
								else {
									numeral+=mas_numeral[0]+mas_numeral[1];
									i++;
								}
							}
							else {
								numeral+=mas_numeral[1]-mas_numeral[2];
								i++;
							}
						}
					} // если записали меньше 3x;
					
				} // при входе было меньше 3х символов
				else {
					if (pos-i>1) {
						int v=0;
						while(i+v<mas_len && w<2) { // проверка не вышли ли за пределы массива
							sym[w++] = op1.charAt(i+v);
							if(sym[w-1]==32) w--; // если пробел в следующем 
							// проходе перезапись
							v++;
						}
					}
					else {
						int v=0;
						while(i+v<mas_len && w<1) { // проверка не вышли ли за пределы массива
							sym[w++] = op1.charAt(i+v);
							if(sym[w-1]==32) {
								w--; // если пробел в следующем 
								System.out.println("Ошибка : неверный оператор1");
								System.exit(-5);
							}
							// проходе перезапись
							v++;
						}
					}
					// здесь обновляем mas_numeral	
					pos_numeral=0; 
					for(int z=0;z<w;z++)
						for(int k=0;k<7;k++)
							if(sym[z]==mas_sym[k][1]) {
								// запись 10х эквивалентов
								mas_numeral[pos_numeral++]=mas_sym[k][0];
								break;
							}
					}
				if(w==2) { // в цикле расчета римских цифр записали только 2
					if(mas_numeral[0]>=mas_numeral[1]) {
						numeral+=mas_numeral[0]+mas_numeral[1];
					}
					else numeral+=mas_numeral[1]-mas_numeral[0];
					i++;
				}
				if(w==1) { // записали один символ
					numeral+=mas_numeral[0];
				}
				w=0;
			}

		}
		else {
			System.out.println("Ошибка: до знака операции не найден операнд");
			System.exit(-3);
		}
		return numeral;
	}

	//Метод возвращает операнд2, end содержит количество символов
	int op(String str,int pos,int end) {
		int numeral=0,w=0;;
		char sym[] = new char[3];
		int mas_numeral[] = new int[3];
		if(pos!=0) {
			String op1 = str.substring(pos+1,end);
			int mas_len=op1.length(); // длинна массива(количество символов)
			// проверяем все ли символы арабские
			for(int i=mas_len-1,p=1; i>-1;i--) {
				 sym[0]= op1.charAt(i);
				 while(i>-1) {
					 if(sym[0]==32) {  //символ пробел?
						 if(i>0) i--;
						 else return numeral;
						 sym[0]= op1.charAt(i);
					 }
					 else break;
				 }
				 if(i>-1) {
					 if(sym[0]>47 && sym[0]<58) { // арабская цифра
						 numeral+= Integer.parseInt(Character.toString(sym[0]))*p;
						 p*=10;
						 type2=1; // флаг значит что была арабская цифра
					 }
					 else if(type2==1) {
						 System.out.println("Ошибка: операнд 2 разные символы");
						 System.exit(-4);
					 }
					 else break; // последняя римская 
				 }
				 
			}
			if(type2==1) return numeral; // выход цифры были арабские
			//ввод римских цифр.
			type2=0;
			int pos_numeral=0;
			for(int i=0;i<mas_len;i++) {
				if(mas_len-i>2) { 
					// запись римских цифр игнорируя пробелы
					int v=0;
					while(i+v<mas_len && w<3) { // проверка не вышли ли за пределы массива
						sym[w++] = op1.charAt(i+v);
						if(sym[w-1]==32) w--; // если пробел в следующем 
						// проходе перезапись
						v++;
					}
					if(w==3) // если записали 3 римских цифры или других
					{
						pos_numeral=0;
						for(int z=0;z<3;z++)
							for(int k=0;k<7;k++)
								if(sym[z]==mas_sym[k][1]) {
									// запись 10х эквивалентов
									mas_numeral[pos_numeral++]=mas_sym[k][0];
									break;
								}
									
						if(pos_numeral<3) { //между римскими непонятные символы
							System.out.println("Ошибка : неверный оператор 2");
							System.exit(-5);
						}
						else {// вычисление числа в 10с/c;
							if(mas_numeral[0]>=mas_numeral[1]) {
								if(mas_numeral[2]>mas_numeral[1]) {
									numeral+=mas_numeral[2]-mas_numeral[1]+mas_numeral[0];
								i+=2;
								}
								else {
									numeral+=mas_numeral[0]+mas_numeral[1];
									i++;
								}
							}
							else {
								numeral+=mas_numeral[1]-mas_numeral[2];
								i++;
							}
						}
					} // если записали меньше 3x;
					
				} // при входе было меньше 3х символов
				else {
					if (mas_len-i>1) {
						int v=0;
						while(i+v<mas_len && w<2) { // проверка не вышли ли за пределы массива
							sym[w++] = op1.charAt(i+v);
							if(sym[w-1]==32) w--; // если пробел в следующем 
							// проходе перезапись
							v++;
						}
					}
					else {
						int v=0;
						while(i+v<mas_len && w<1) { // проверка не вышли ли за пределы массива
							sym[w++] = op1.charAt(i+v);
							if(sym[w-1]==32) {
								w--; // если пробел в следующем 
								System.out.println("Ошибка : неверный оператор1");
								System.exit(-5);
							}
							// проходе перезапись
							v++;
						}
					}
					// здесь обновляем mas_numeral	
					pos_numeral=0; // здесь изменил неосознанно.
					for(int z=0;z<w;z++)
						for(int k=0;k<7;k++)
							if(sym[z]==mas_sym[k][1]) {
								// запись 10х эквивалентов
								mas_numeral[pos_numeral++]=mas_sym[k][0];
								break;
							}
					}
				if(w==2) { // в цикле расчета римских цифр записали только 2
					if(mas_numeral[0]>=mas_numeral[1]) {
						numeral+=mas_numeral[0]+mas_numeral[1];
					}
					else numeral+=mas_numeral[1]-mas_numeral[0];
					i++;
				}
				if(w==1) { // записали один символ
					numeral+=mas_numeral[0];
				}
				w=0;
			}

		}
		else {
			System.out.println("Ошибка: до знака операции не найден операнд");
			System.exit(-3);
		}
		return numeral;
	}
	
	// Метод возвращает указатель на массив из 2х элементов содержащие 2 операнда
	static int[] operands(int op1, int op2) {
		if(type==type2) { // операнды одного типа
			int oper[]= new int[2];
			oper[0]=op1;
			oper[1]=op2;
			return oper;
		}
		else {
			System.out.println("Ошибка: операторы разного типа");
			System.exit(-6);
		}
		return null;
	}

	//Метод вычисляющий заданное выражение. Возвращает тип int;
	int result(String str,int pos,int end) {
		int mas[]= operands(op(str,pos),op(str,pos,end));
		int res=0;
		char c = str.charAt(pos);
		switch (c) {
			case '+': res = mas[0]+mas[1]; break;
			case '-': res = mas[0]-mas[1]; break;
			case '*': res = mas[0]*mas[1]; break; 
			case '/': {
				if(mas[1]!=0) { res = mas[0]/mas[1];}
				else {System.out.println("Ошибка : деление на 0");
				System.exit(-7);
				} 
				break; 
			}
		}
		return res;
	}

	// Метод выводящий результат вычислений, либо арабскими цифрами, либо римскими
	void print_res(int res) {
		StringBuffer str1 = new StringBuffer(52);
		if(type==0) {// римские цифры
			int del=1000;
			int c=6; // спецификатор доступа к массиву символов;
			int cel=0,ost=0;
			while(del!=0) {
				cel = res/del;
				ost = res%del;
				if(cel!=0) {
					if(del==1000) { // если тысячи заполняем M - 1000
						for(int i=0;i<cel;i++) {
							str1.append((char)mas_sym[c][1]);
						}
					}
					else {
						
						if(cel==9) {
							str1.append((char)mas_sym[c][1]);
							str1.append((char)mas_sym[c+2][1]);	
						}
						else { 
							if(cel==4) {
							str1.append((char)mas_sym[c][1]);
							str1.append((char)mas_sym[c+1][1]);
							}
							else {
								if(cel>=5) {
									int i = cel-5;
									str1.append((char)mas_sym[c+1][1]);
									while(i>0) {
										str1.append((char)mas_sym[c][1]);
										i--;
									}
								}
								else while(cel>0){
									cel--;
									str1.append((char)mas_sym[c][1]);
								}
							}
						
						}
						
					}
				}
				del/=10;
				res=ost;
				if(c!=0) c-=2;
			}
			// выводим римские цифры;
			System.out.println(str1.toString());
		}
		else System.out.println(res);
	}
}