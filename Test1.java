import java.util.Scanner; 


/* Батарон Д.А. исправлены :
 * 
 * 1) Именования классов методов и переменных не соответствующие
 * конвенции Java.
 * 
 * 2) Удален дублирующийся код.
 * 
 * 3) Переписано в соответствии с принципом единственной ответственности
 * класса.
 */


public class Calc2 {
	public static void main(String[] args) {
		Calculator my = new Calculator();
		my.getStart();
	}
}


class Calculator {
	public void getStart() {
		String inStr = IO.inCon();
		int posOperator = FindOp.searchOp(inStr);
		int operand1 = Operand.findOp(inStr, 0, posOperator);
		TypeComp.typeCom(Operand.getType());
		int operand2 = Operand.findOp(inStr, posOperator, inStr.length());	
		if(TypeComp.typeCom(Operand.getType()) != 1) {
			System.out.println("Ошибка: разные операнды");
			System.exit(-10);
		}
		int result = Calculation.result(inStr, posOperator,
				operand1, operand2);
		IO.printRes(result);
	}
	
}


// Класс ввода вывода.
class IO{
	
	public static String inCon() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ввод выражения: ");
		String str = sc.nextLine();
		return str;
	}
	
	// Метод выводящий результат вычислений, либо арабскими цифрами,
	//либо римскими
	public static void printRes(int res) {
		StringBuffer str1 = new StringBuffer(52);
		if(Operand.getType()==0) {// римские цифры
			int del=1000;
			int c=6; // спецификатор доступа к массиву символов;
			int cel=0,ost=0;
			while(del!=0) {
				cel = res/del;
				ost = res%del;
				if(cel!=0) {
					if(del==1000) { // если тысячи заполняем M - 1000
						for(int i=0;i<cel;i++) {
							str1.append((char)Operand.getRomSymbols(c, 1));
						}
					}
					else {
						
						if(cel==9) {
							str1.append((char)Operand.getRomSymbols(c,1));
							str1.append((char)Operand.getRomSymbols(c+2,1));	
						}
						else { 
							if(cel==4) {
							str1.append((char)Operand.getRomSymbols(c,1));
							str1.append((char)Operand.getRomSymbols(c+1,1));
							}
							else {
								if(cel>=5) {
									int i = cel-5;
									str1.append((char)Operand.getRomSymbols(c+1,1));
									while(i>0) {
										str1.append((char)Operand.getRomSymbols(c,1));
										i--;
									}
								}
								else while(cel>0){
									cel--;
									str1.append((char)Operand.getRomSymbols(c,1));
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


//поиск знака операции  возвращает его позицию в строке
class FindOp{
	public static int searchOp(String str) {
		int index=-1;
		int index2;
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
					if((fl==1) || (index2!=index)) {
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


//класс проверки типов операндов
class TypeComp{
	private static int type;
	public static int typeCom(int type2) {
		int t;
		if(type==type2) t=1;
		else t=0;
		type = type2;
		return t;
	}
}


//класс вычисляющий выражение
class Calculation{
	
	static int result(String str,int pos,int operand1, int operand2) {
		int res=0;
		char c = str.charAt(pos);
		switch (c) {
			case '+': res = operand1+operand2; break;
			case '-': res = operand1-operand2; break;
			case '*': res = operand1*operand2; break; 
			case '/': {
				if(operand2!=0) { res = operand1/operand2;}
				else {System.out.println("Ошибка : деление на 0");
				System.exit(-7);
				} 
				break; 
			}
		}
		return res;
	}
}


// класс получающий операторы
class Operand {
	private final static int MAS_SYM[][]= {
			{1,73}, // I 73
			{5,86}, // V 86
			{10,88}, // X 88
			{50,76}, //L 76
			{100,67}, //C 67
			{500,68}, //D 68
			{1000,77} //M 77
	};
	private static int type2=0; // 0 значит цифры римские

	//Метод возвращает операнд. pos - позиция либо знака операции либо 
	// начала операнда.
	public static int findOp(String str,int pos,int end) {
		int numeral=0; // конечное число;
		int w=0; // сколько цифр было в цикле
		char sym[] = new char[3];
		int mas_numeral[] = new int[3]; // строка с римскими цифрами
		String op1; // строка содержащая оператор и пробелы
		if( (pos==end) || (pos+1 == end && end>1) )
		{
			if(end==0) { 
				System.out.println("Ошибка: Оператор 1 отсутствует");
				System.exit(-8);
				}
			else {
				System.out.println("Ошибка: Оператор 2 отсутствует");
				System.exit(-9);
			}
		}
		if(pos==0)
			op1 = str.substring(pos,end);
		else
			op1 = str.substring(pos+1,end);
		
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
								if(sym[z]==MAS_SYM[k][1]) {
									// запись 10х эквивалентов
									mas_numeral[pos_numeral++]=MAS_SYM[k][0];
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
					pos_numeral=0; 
					for(int z=0;z<w;z++)
						for(int k=0;k<7;k++)
							if(sym[z]==MAS_SYM[k][1]) {
								// запись 10х эквивалентов
								mas_numeral[pos_numeral++]=MAS_SYM[k][0];
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
			return numeral;
	}
	
	//Метод доступа к массиву римских символов MAS_SYM
	public static int getRomSymbols(int i,int j) {
		return MAS_SYM[i][j];
	}
	
	public static int getType() {
		return type2;
	}
}