abstract class public_transport {
    int juu;                        //주유량
    int nowspeed;                   //현재속도
    int maxpas,nowpas;
    int cost;                       //요금
    boolean state=true;                   //상태

    abstract void start_service();      //운행 메소드

    void pass_boarding(int addpas) {
        if((nowpas+addpas)>maxpas){
            System.out.println("사람 초과입니다.");
            nowpas=maxpas;
            System.out.println("현재 사람수는 : "+ nowpas +"명 입니다.");
            return;
        } else if (state!=true) {
            System.out.println("사람이 탈 수 없는 상태입니다.");
            System.out.println("현재 사람수는 : "+ nowpas +"명 입니다.");
        } else{
            nowpas +=addpas;
            System.out.println("사람이 "+ addpas+"명 탔습니다.");
            System.out.println("현재 사람수는 : "+ nowpas +"명 입니다.");
        }

    }; // 승객탑승 메소드

    public void change_state(){
        state = state?false:true;
        if(juu<10) System.out.println("주유가 필요하다.");
    }; // 상태 변경 메소드

    public void change_speed(int speed) {
        if(juu<10){
            System.out.println("주유량을 확인해주세요.");
            change_state();
            return;
        }
        else{
            nowspeed+=speed;
            System.out.println("현재 속도는 : "+ nowspeed +"km/h 입니다.");
        }
    }   //속도변경 메소드
}
