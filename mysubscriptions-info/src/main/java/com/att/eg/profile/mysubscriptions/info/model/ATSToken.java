package com.att.eg.profile.mysubscriptions.info.model;

public class ATSToken {
        private String atsTokenId; //this variable cannot be atsToken (same as class name)

        public ATSToken(String atsTokenId) {
            this.setAtsToken(atsTokenId);
        }

        public String getAtsToken() {
            return atsTokenId;
        }

        public void setAtsToken(String atsToken) {
            this.atsTokenId = atsToken;
        }
}
