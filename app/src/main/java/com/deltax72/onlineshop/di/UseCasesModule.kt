package com.deltax72.onlineshop.di

val useCasesModule: UseCasesModule by lazy(::UseCasesModuleImpl)

interface UseCasesModule {

}

class UseCasesModuleImpl : UseCasesModule {

}