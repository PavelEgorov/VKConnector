package com.egorovsoft.vkconnector.mvp.model

import com.google.gson.annotations.Expose

class Responce<T> (
    @Expose val response: T
)