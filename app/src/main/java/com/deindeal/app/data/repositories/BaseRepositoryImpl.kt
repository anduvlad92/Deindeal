package com.deindeal.app.data.repositories

import com.deindeal.app.data.sources.api.services.BaseService


/**
 * BaseRepositoryImpl
 * Should contain possible data sources (api, database, sharedpreferences etc) for a specific data type
 */
abstract class BaseRepositoryImpl<SERVICE : BaseService>(protected val apiService: SERVICE)