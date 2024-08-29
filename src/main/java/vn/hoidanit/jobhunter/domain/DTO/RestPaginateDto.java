package vn.hoidanit.jobhunter.domain.DTO;

import java.util.List;

public class RestPaginateDto<T> {
    private Meta meta;

    private List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    public static class Meta {
        int current;
        int pageSize;
        int totalsPage;
        int totalsItems;

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalsPage() {
            return totalsPage;
        }

        public void setTotalsPage(int totalsPage) {
            this.totalsPage = totalsPage;
        }

        public int getTotalsItems() {
            return totalsItems;
        }

        public void setTotalsItems(int totalsItems) {
            this.totalsItems = totalsItems;
        }
    }
}
