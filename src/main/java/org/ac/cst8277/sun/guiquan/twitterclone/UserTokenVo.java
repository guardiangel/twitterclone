package org.ac.cst8277.sun.guiquan.twitterclone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenVo implements Serializable {
    private String userId;
    private String token;
}
