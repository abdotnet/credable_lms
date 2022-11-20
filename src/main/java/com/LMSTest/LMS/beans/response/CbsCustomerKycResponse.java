package com.LMSTest.LMS.beans.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CbsCustomerKycResponse {
    private Date createdAt;
    private Date createdDate;
    private int customerNumber;
    private String email;
    private String firstName;
    private String gender;
    private int id;
    private int idNumber;
    private String idType;
    private String lastName;
    private String middleName;
    private String mobile;
    private double monthlyIncome;
    private String status;
    private Date updatedAt;
}





