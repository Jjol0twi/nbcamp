import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.swing.text.DateFormatter

fun main() {
    println("호텔예약 프로그램입니다.\n[메뉴]\n|1. 방예약| |2. 예약목록 출력| |3. 예약목록 출력(정렬)| |4. 시스템 종료| |5. 금액 입금-출금 내역 출력| |6. 예약 변경/취소| ")
    val manualText: Array<String> = arrayOf(
        "예약자분의 성함을 입력해주세요.",
        "예약할 방 번호를 입력해주세요.",
        "체크인 날짜를 입력해주세요. 표기형식) yyyyMMdd",
        "체크아웃 날짜를 입력해주세요. 표기형식) yyyyMMdd",
    )
    var selected = readLine()?.toIntOrNull()
    when (selected) {
        1 -> for (i in manualText) {
            println(i)
            var reservationRoomValue = readLine()
            if (reservationRoomValue.isNullOrBlank()) {
                println("아무것도 입력되지 않았습니다. 다시 입력해주세요.")
                break
            }
            val dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
            val today= LocalDate.parse(LocalDate.now().toString(),dateFormatter)
            var checkInDate : LocalDate
            if (manualText.indexOf(i) == 1 && (reservationRoomValue.toInt() < 100 || reservationRoomValue.toInt() > 999)) println(
                "올바르지 않는 방 번호입니다. 방 번호는 100~999 영역 이내입니다."
            )
            if (manualText.indexOf(i)==2){
                if (!today.isBefore(LocalDate.parse(i,dateFormatter))){
                    println("체크인은 지난날을 선택할 수 없습니다.")
                    break
                }
                checkInDate= LocalDate.parse(reservationRoomValue, dateFormatter)
            }
            if (manualText.indexOf(i)==3&&checkInDate!=null){
                if (checkInDate.isBefore(LocalDate.parse(i,dateFormatter))){

                }
            }
        }
    }
}
