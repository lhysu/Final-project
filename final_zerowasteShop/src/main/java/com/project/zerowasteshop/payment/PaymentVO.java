package com.project.zerowasteshop.payment;

import lombok.Data;

@Data
public class PaymentVO {
	private int pay_num;            // 결제 번호 (Primary Key)
    private String merchant_uid;    // 주문 번호
    private String imp_uid;         // 아임포트 결제 고유 ID
    private String member_id;       // 회원 ID
    private int paid_amount;        // 결제 금액
    private String pay_method;      // 결제 방식 (카드, 계좌이체 등)
    private String pay_status;      // 결제 상태 ('paid', 'failed' 등)
    private String pay_date;        // 결제일
    private String tracking_number; // 운송장 번호

}
