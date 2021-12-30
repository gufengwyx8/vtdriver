/*
 * Copyright 1999-2017 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jd.jdbc.sqlparser.ast.statement;

import com.jd.jdbc.sqlparser.ast.SQLExpr;
import com.jd.jdbc.sqlparser.visitor.SQLASTVisitor;

public class SQLColumnCheck extends SQLConstraintImpl implements SQLColumnConstraint {

    private SQLExpr expr;

    public SQLColumnCheck() {

    }

    public SQLColumnCheck(SQLExpr expr) {
        this.setExpr(expr);
    }

    public SQLExpr getExpr() {
        return expr;
    }

    public void setExpr(SQLExpr expr) {
        if (expr != null) {
            expr.setParent(this);
        }
        this.expr = expr;
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.getName());
            acceptChild(visitor, this.getExpr());
        }
        visitor.endVisit(this);
    }

    public SQLColumnCheck clone() {
        SQLColumnCheck x = new SQLColumnCheck();

        super.cloneTo(x);

        if (expr != null) {
            x.setExpr(expr.clone());
        }

        return x;
    }

}