package com.migrosone.mobiletestautomation.pages

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule
import com.migrosone.mobiletestautomation.constants.SignUpConstants

class SignUpPage(private val rule: ComposeTestRule) {

    fun assertHomePageFieldsVisible() {
        rule
            .onNodeWithContentDescription(SignUpConstants.LOGO_DESC, useUnmergedTree = true)
            .assertIsDisplayed()
        rule.onNodeWithText(SignUpConstants.SIGN_IN_TEXT, useUnmergedTree = true)
            .assertIsDisplayed()
        rule.onNodeWithText(SignUpConstants.SIGN_UP_TEXT, useUnmergedTree = true)
            .assertIsDisplayed()
    }


    fun clickOnSingUpButton() {
        rule.onNodeWithText(SignUpConstants.SIGN_UP_TEXT, useUnmergedTree = true)
            .performClick()
    }

    fun assertSignUpTitleExist() {
        rule
            .onAllNodesWithText(SignUpConstants.SIGN_UP_TEXT, useUnmergedTree = true)[0]
            .assertIsDisplayed()
    }

    fun checkEmailFieldDisplayed() {
        rule.onNodeWithText(SignUpConstants.EMAIL_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun checkPasswordFieldDisplayed() {
        rule.onNodeWithText(SignUpConstants.PASSWORD_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun checkNameFieldDisplayed() {
        rule.onNodeWithText(SignUpConstants.NAME_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun assertSignUpFieldsDisplayed() {
        checkEmailFieldDisplayed()
        checkPasswordFieldDisplayed()
        checkNameFieldDisplayed()
    }

    fun setEmailAddressToForm() {
        // Email
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[0]
            .performTextInput(SignUpConstants.VALID_EMAIL)
    }

    fun setValidPasswordToForm() {
        // Password
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[1]
            .performTextInput(SignUpConstants.VALID_PASSWORD)
    }

    fun setValidNameToForm() {
        // Name
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[2]
            .performTextInput(SignUpConstants.VALID_NAME)
    }

    fun fillFormSignUpPage() {
        // Email
        setEmailAddressToForm()

        // Password
        setValidPasswordToForm()

        // Name
        setValidNameToForm()
    }

    fun clickOnSignUpButtonAllNodesWithText() {
        rule
            .onAllNodesWithText(SignUpConstants.SIGN_UP_TEXT, useUnmergedTree = true)[1]
            .performClick()
    }

    fun waitSuccessMembershipMessage() {
        rule.waitUntil(timeoutMillis = 5000) {
            rule.onAllNodesWithText(SignUpConstants.SUCCESS_MESSAGE)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
    }


    fun assertSuccessMembershipMessage() {
        rule.onNodeWithText(SignUpConstants.SUCCESS_MESSAGE).assertIsDisplayed()
    }

    fun assertSignInNowButtonDisplayed() {
        rule.onNodeWithText(SignUpConstants.SIGN_IN_NOW_TEXT, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun clickOnSignInNowButton() {
        rule.onAllNodesWithText(
            SignUpConstants.SIGN_IN_NOW_TEXT,
            useUnmergedTree = true
        )[0].performClick()
    }

    fun assertSignInScreenOpen() {
        rule.waitUntil(timeoutMillis = 5000) {
            rule.onAllNodesWithText(SignUpConstants.SIGN_IN_TEXT)
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        rule
            .onAllNodesWithText(SignUpConstants.SIGN_IN_TEXT, useUnmergedTree = true)[0]
            .assertIsDisplayed()
    }


    fun assertNotEqualsSuccessMembershipMessage() {
        rule.onNodeWithText(SignUpConstants.SUCCESS_MESSAGE).assertIsNotDisplayed()
    }

    fun setValidName() {
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[2]
            .performTextInput(SignUpConstants.VALID_NAME)
    }

    fun setValidPassword() {
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[1]
            .performTextInput(SignUpConstants.VALID_PASSWORD)
    }

    fun setValidEmail() {
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[0]
            .performTextInput(SignUpConstants.VALID_EMAIL)
    }

    fun setInvalidEmail() {
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[0]
            .performTextInput(SignUpConstants.INVALID_EMAIL)

    }
}