package kr.co.tjoeun.listview_20200810.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.listview_20200810.R
import kr.co.tjoeun.listview_20200810.datas.Student

class StudentAdapter(
    val mContext: Context,
    val resId: Int,
    val mList: List<Student>
) : ArrayAdapter<Student>(mContext, resId, mList){

    // xml을 가지고 객체로 변환(Inflate) 해주는 변수
    val inf = LayoutInflater.from(mContext)

    // 각각의 줄에 뿌려질 모양과 데이터를 조합하는 함수
    // 기본 모양이 아니라 우리가 그린 xml로 뿌려지게 하려면 -> 오버라이딩을 커스터마이징해야한다.
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //return super.getView(position, convertView, parent)
        // ConvertView 변수는 내용물 변경이 불가.
        // -> tempRow를 var로 만들어서 담아주면 상황에 따라 내용물을 변경할 수 있다.
        var tempRow = convertView

        // 상황 : tempRow(convertView)에 아무것도 안담겨 있다면
        if(tempRow == null){

            // student_list_item의 모양을 tempRow에 담아서 채우자
            tempRow = inf.inflate(R.layout.student_list_item, null)
        }

        // tempRow에는 절대 null일 가능성이 없다. -> row에 절대 null이 아니라고 하면서(!!) 대입
        // 각각의 줄에 뿌려질 모양이 row에 담긴다.
        val row = tempRow!!

        // row 뿌려지기 전에 -> 데이터와 조합해서 적당히 문구 등을 수정하고 나서 그 다음
        // 상황에 맞게 (position에 맞게) 뿌려줄 근거 데이터 변수 (mList에서) 가져오기
        val data = mList[position]

        //뿌려줄 row 안에 있는 텍스트뷰를 변수로 담자
        // UI에 근거 데이터 반영하기
        val nameTxt = row.findViewById<TextView>(R.id.nameTxt)
        //거주지정보를 형태를 가공해서 출력
        val addressTxt = row.findViewById<TextView>(R.id.addressTxt)
        val ageAndGendertxt = row.findViewById<TextView>(R.id.ageAndGendertxt)


        nameTxt.text = data.name
        addressTxt.text = "(${data.address})"

        // data내부의 isMale, birthYear을 가지고 -> 가공하자.
        // 성별이 어떻게 되는지?
        var genderStr = "여성"
        if (data.isMale) {
            genderStr = "남성"
        }

        // 올해 나이가 어떻게 되는지? 2020-본인생년 + 1 = 한국나이
        val age = 2020- data.birthYear +1

        ageAndGendertxt.text = "${data.getKoreaAge()}세, ${data.getGenderString()}"

        // 완성된 row를 각각의 한 줄로 뿌려주세요라고 리턴처리
        return row
    }
}